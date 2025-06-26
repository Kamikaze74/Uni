/**
 * BEARBEITUNGSHINWEIS:
 * Es müssen eigentlich nur die Funktion custom_setup und custom_loop bearbeitet werden.
 * Wer möchte kann aber auch weitere Änderungen vornehmen!
 * Denkt daran: Jede Änderung hier bedeutet dass das EduTerm neu geflasht werden muss!
 */

#include <Arduino.h>
#include <WiFiMulti.h>
#include <WiFiUdp.h>
#include <Adafruit_NeoPixel.h>
#include <Bounce2.h>
#include <ESP32Encoder.h>
#include <lcd_esp.h>
#include <monitor.h>
#include "../../dataframe.h"

// Wi-Fi connection settings
static const char *SSID = "EMSYS";
static const char *PASSWORD = "cheeta42";
static WiFiUDP udp;

// RW1073 Display settings
constexpr uint8_t BG_LIGHT = 32;
constexpr uint8_t SSD_SS = SS;
constexpr uint8_t SSD_MISO = MISO;
constexpr uint8_t SSD_MOSI = MOSI;
constexpr uint8_t SSD_SCK = SCK;
constexpr uint8_t RESET_PIN = 25;
static RW1073 lcd(SSD_SS, SSD_MISO, SSD_MOSI, SSD_SCK, RESET_PIN);
constexpr uint8_t CHAR_SMILEY = 0;
constexpr uint8_t CHAR_FROWNIE = 1;

// NeoPixel status RGB LED settings
constexpr int16_t LED_PIN = 27;    // Which pin is connected to the NeoPixel
constexpr uint16_t NUMPIXELS = 1;  // Number of connected RGB LEDS
static Adafruit_NeoPixel strip(NUMPIXELS, LED_PIN, NEO_GRB + NEO_KHZ800);
static uint32_t COL_BLK;
static uint32_t COL_RED;
static uint32_t COL_GRN;

// Rotary Encoder settings
constexpr int ENC_A = 17;
constexpr int ENC_B = 16;
constexpr uint8_t ENC_SW = 15;
static ESP32Encoder encoder;
static Bounce encButton = Bounce();
static int64_t encoder_count;

// Buzzer settings
constexpr uint8_t BUZZER = 33;

// Joystick settings
constexpr uint8_t switchU = 34;
constexpr uint8_t switchR = 35;
constexpr uint8_t switchD = 12;
constexpr uint8_t switchL = 0;
constexpr uint8_t joystick[4] = {switchU, switchR, switchD, switchL};
static uint8_t js_state;
#define JS_UP    ((js_state & 1<<0) ? false : true)
#define JS_RIGHT ((js_state & 1<<1) ? false : true)
#define JS_DOWN  ((js_state & 1<<2) ? false : true)
#define JS_LEFT  ((js_state & 1<<3) ? false : true)

void init_buzzer();
void init_display();
void init_encoder();
void init_joystick();
void init_status_led();
void init_wifi();
void actionLoop(bool *act_joystick, bool *act_encoder, bool *act_button);
void buzzer_switch(bool on);
void buzzer_signal(uint16_t ms);
int receive_job_msg(void *message, int msg_size);
void print_menu();

/*
 * Hier Globale Variablen deklarieren:
 */
#define MAX_PAGES 10
page_t pages[MAX_PAGES];
int current_page = 0;
/*
 * Hier einmalige Einrichtungen ausführen!
 */
void custom_setup () {
    for (int i=0; i<MAX_PAGES; i++)
        pageclear(&pages[i]);
}

/*
 * Hier werden immer wieder Ereignisse geprüft, wie z.B.:
 *  - Nachricht eingegangen
 *  - Knopf gedrückt
 *  - Rad gedreht
 */
void custom_loop (bool *joystick_action, bool *encoder_action, bool *button_action) {
    dataframe frame;
    // Trying to get new job message via network
    if (receive_job_msg(&frame, sizeof(frame)) == sizeof(frame))
        if (frame.pageNo >= 0 && frame.pageNo < MAX_PAGES - 1){
            Serial.printf("New page %d\n", frame.pageNo);
            memcpy(pages[frame.pageNo], frame.page, sizeof(page_t));
        }

    //Check encoder rotation
    if (*encoder_action) {
        current_page = (encoder_count < 0 ? 0 : encoder_count) % MAX_PAGES;
    }

    //Update Display
    lcd.setPos(0,0); lcd.printf(pages[current_page][0]);
    lcd.setPos(0,1); lcd.printf(pages[current_page][1]);
    lcd.setPos(0,2); lcd.printf(pages[current_page][2]);
    lcd.setPos(0,3); lcd.printf(pages[current_page][3]);

    /*
    Serial.printf("----- Page: %2d -----\n", current_page);
    Serial.printf("%s\n", pages[current_page][0]);
    Serial.printf("%s\n", pages[current_page][1]);
    Serial.printf("%s\n", pages[current_page][2]);
    Serial.printf("%s\n", pages[current_page][3]);
    Serial.println("--------------------");
    */
}

void setup() {
    // Serial setup enables also error messages from underlying modules
    delay(1536);
    Serial.begin(115200);
    Serial.println(F(""));
    Serial.println(F("--- EduTerm Firmware for Embedded Systems v1.0rc1 ---"));

    // This is an RGB LED strip for status indication and the buzzer for informing the user
    init_status_led();
    init_buzzer();

    // LCD setup and some messages to be printed by it
    init_display();
    lcd.print(F(" - E d u T e r m -  "));
    lcd.setPos(0, 1), lcd.print(F("Wi-Fi login to SSID "));
    lcd.setPos(0, 2), lcd.printf("'%s' ...", SSID);

    custom_setup();

    // Wi-Fi is important, because this is a server.  We cannot do anything without it.
    init_wifi();        // !!!This uses the status LED!!! (init_status_led first)
    print_menu();
    buzzer_signal(100);

    init_joystick();    // 'Joystick like' button field
    init_encoder();     // Rotary Encoder
}

void loop() {
    // TODO: To be outputted on display.

    // Observing for user action
    bool joystick_action, encoder_action, button_action;
    actionLoop(&joystick_action, &encoder_action, &button_action);
    custom_loop(&joystick_action, &encoder_action, &button_action);
    if (joystick_action || encoder_action || button_action) {
        lcd.clear();
        lcd.homePos();
        if (joystick_action) {
            Serial.println(F("---"));
            Serial.printf("Joystick U: %c\n", (JS_UP ? '+' : '-'));
            Serial.printf("Joystick R: %c\n", (JS_RIGHT ? '+' : '-'));
            Serial.printf("Joystick D: %c\n", (JS_DOWN ? '+' : '-'));
            Serial.printf("Joystick L: %c\n", (JS_LEFT ? '+' : '-'));
        }
        if (encoder_action) {
            //Serial.printf("Encoder: %lli\n", encoder_count);
        }
        if (button_action) {
            Serial.printf("EncButton: %c\n", (encButton.read() ? '-' : '+'));
            if (!encButton.read()) {
                encoder.clearCount();
                encoder_count = encoder.getCount();
                //Serial.printf("Encoder: %lli\n", encoder_count);
            } else if (encButton.previousDuration() >= 5000) {
                // After 5 seconds we do a reset
                Serial.println(F("---"));
                Serial.println(F("Reset requested by user..."));
                ESP.restart();
            } else
                print_menu();
        }
    }
}

void init_buzzer() {
    pinMode(BUZZER, OUTPUT);
    digitalWrite(BUZZER, LOW);  // Turn off buzzer
}

void init_display() {
    // Define 2 custom characters to be copied in the display controller
    uint8_t smiley[8] = {
            0b00000,
            0b00000,
            0b01010,
            0b00000,
            0b00000,
            0b10001,
            0b01110,
            0b00000}; // :)
    uint8_t frownie[8] = {
            0b00000,
            0b00000,
            0b01010,
            0b00000,
            0b00000,
            0b00000,
            0b01110,
            0b10001}; // :(

    // The background display is controlled directly from the microcontroller
    pinMode(BG_LIGHT, OUTPUT);
    digitalWrite(BG_LIGHT, HIGH);

    lcd.begin(HD44780::R4, HD44780::C20);
    lcd.cursorOff();
    lcd.create(CHAR_SMILEY, smiley);    // Store user defined character ':)'
    lcd.create(CHAR_FROWNIE, frownie);  // Store user defined character ':('
    lcd.homePos();
}

void init_encoder() {
    encoder.attachSingleEdge(ENC_A, ENC_B);
    encoder.clearCount();
    encButton.attach(ENC_SW, INPUT);
    encButton.interval(5);  // Debounce intervall in ms
    pinMode(ENC_SW, INPUT);
}

void init_joystick() {
    pinMode(switchU, INPUT);
    pinMode(switchR, INPUT);
    pinMode(switchD, INPUT_PULLUP);
    pinMode(switchL, INPUT);
    delay(100);
    for (int i = 0; i < 4; i++) {
        js_state |= digitalRead(joystick[i]) << i;
    }
}

void init_status_led() {
    strip.begin();
    strip.show();
    strip.setBrightness(50);  // Set BRIGHTNESS to about 1/5 (max = 255)
    COL_BLK = Adafruit_NeoPixel::Color(0, 0, 0);
    COL_RED = Adafruit_NeoPixel::Color(255, 0, 0);
    COL_GRN = Adafruit_NeoPixel::Color(0, 255, 0);
}

// You should call init_status_led first to activate status LED
void init_wifi() {
    Serial.printf("Connecting with Wi-Fi '%s' ", SSID);
    WiFiClass::mode(WIFI_STA);
    WiFi.begin(SSID, PASSWORD);
    bool led_state = true;
    for (int i = 1; true; i++) {
        if (WiFiClass::status() == WL_CONNECTED)
            break;
        // Output something like "....(5)....(10).. etc."
        delay(500);
        if (i % 10)
            Serial.print(F("."));
        else
            Serial.printf("(%is)", i / 2);
        // Toggle status LED
        led_state = !led_state;
        if (led_state)
            strip.setPixelColor(0, COL_RED);
        else
            strip.setPixelColor(0, COL_BLK);
        strip.show();
    }
    strip.setPixelColor(0, COL_GRN);
    strip.show();
    // Serial info
    Serial.println(F(""));
    Serial.printf("Connected to Wi-Fi '%s'\n", SSID);
    Serial.print(F("(now I am client '"));
    Serial.print(WiFi.localIP());
    Serial.println(F("')"));
    randomSeed(micros());  // A good moment to init random.
    if (udp.begin(MY_UDP_PORT) == 0) {
        Serial.println(F("Unable to open my UDP port--going to try again later..."));
        strip.setPixelColor(0, COL_RED);
        strip.show();
        buzzer_signal(500);
        delay(5000);
        ESP.restart();
    }
}

void actionLoop(bool *act_joystick, bool *act_encoder, bool *act_button) {
    // Init them
    *act_joystick = *act_encoder = *act_button = false;
    encButton.update();
    // Joystick evaluation
    uint8_t js_new_state = 0;
    for (int i = 0; i < 4; i++)
        js_new_state |= digitalRead(joystick[i]) << i;
    if (js_new_state != js_state) {
        js_state = js_new_state;
        *act_joystick = true;
    }
    // Encoder evaluation
    int64_t enc_new_count = encoder.getCount();
    if (enc_new_count != encoder_count) {
        encoder_count = enc_new_count;
        *act_encoder = true;
    }
    // Button evaluation
    *act_button = encButton.changed();
}

void buzzer_switch(bool on) {
    if (on)
        digitalWrite(BUZZER, HIGH);
    else
        digitalWrite(BUZZER, LOW);
}

// TODO: This should be made asynchronous
void buzzer_signal(uint16_t ms) {
    ms = (ms <= 2000) ? ms : 2000;
    buzzer_switch(true);
    delay(ms);
    buzzer_switch(false);
}

void print_menu() {
    lcd.setPos(0, 0), lcd.print(F(" - E d u T e r m -  "));
    lcd.setPos(0, 1), lcd.print(F("Now my IP address is"));
    lcd.setPos(0, 2), lcd.print(F("                    "));  // Erase line
    lcd.setPos(0, 2), lcd.printf("'%s'", WiFi.localIP().toString().c_str());
    lcd.setPos(0, 3), lcd.print(F("(waiting for data) "));
    lcd.write(CHAR_SMILEY);
}

int receive_job_msg(void *message, int msg_size) {
    int received_size = 0;
    bzero(message, msg_size);
    int packet_size = udp.parsePacket();
    if (packet_size) {
        Serial.println();
        Serial.print(F("Noticed packet of size "));
        Serial.println(packet_size);
        Serial.print(F("from "));
        IPAddress remoteIp = udp.remoteIP();
        Serial.print(remoteIp);
        Serial.print(F(", port "));
        Serial.println(udp.remotePort());
        if (packet_size == msg_size) {
            received_size = udp.read((unsigned char *) message, msg_size);
            if (received_size == msg_size) {
                Serial.println(F("Datagram of expected size has been read"));
                buzzer_signal(40);
                delay(50);
                buzzer_signal(40);
            } else {
                received_size = 0;
                Serial.println(F("Failure: Datagram has been partially read"));
            }
        }
        udp.flush();  // Finish reading the current packet
    }
    return received_size;
}

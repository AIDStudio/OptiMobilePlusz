#!/bin/bash

# Színek a logoláshoz
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
NC='\033[0m' # No Color

echo -e "${BLUE}=== OptiMobile Plusz Auto-Build ===${NC}"

# 1. Tisztítás és Build
echo -e "${GREEN}[1/3] Build folyamat indítása...${NC}"
./gradlew clean build

# Ellenőrizzük, hogy sikeres volt-e a build
if [ $? -eq 0 ]; then
    echo -e "${GREEN} ✅ Build SIKERES!${NC}"
else
    echo -e "${RED} ❌ Hiba történt a build során!${NC}"
    exit 1
fi

# 2. A kész fájl beállítása a megadott útvonal alapján
echo -e "${GREEN}[2/3] Fájl ellenőrzése...${NC}"

# A pontos útvonal a Termux belső rendszerében
JAR_FILE="build/libs/optimobileplusz-1.1.0.jar"

if [ ! -f "$JAR_FILE" ]; then
    echo -e "${RED} ❌ Nem találom a fájlt: $JAR_FILE${NC}"
    echo -e "${BLUE} Lehetséges, hogy a verziószám eltér. Aktuális fájlok:${NC}"
    ls -F build/libs/
    exit 1
fi

echo -e "${BLUE}Talált fájl: $JAR_FILE${NC}"

# 3. Másolás a telefon tárhelyére
echo -e "${GREEN}[3/3] Másolás a telefon tárhelyére...${NC}"

# Célmappa a telefonodon (Mc_Mod_Build mappa)
DEST_DIR="/sdcard/Mc_Mod_Build/OptiMobilePlusz/build/libs"
mkdir -p "$DEST_DIR"

cp "$JAR_FILE" "$DEST_DIR/"

if [ $? -eq 0 ]; then
    echo -e "${GREEN} ✅ KÉSZ! A modot itt találod: $DEST_DIR${NC}"
    echo -e "${BLUE} Fájlnév: $(basename "$JAR_FILE")${NC}"
else
    echo -e "${RED} ❌ Nem sikerült a másolás!${NC}"
    echo -e "Futtasd: termux-setup-storage${NC}"
fi

echo -e "${BLUE}====================================${NC}"

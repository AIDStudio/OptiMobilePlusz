# OptiMobilePlusz Kezelőpult (v1.1 Update)

Üdvözöl az **OptiMobilePlusz** hivatalos, Zalith Launcher-re optimalizált kezdőlapja! Ez a mod egy nagy teljesítményű optimalizációs csomag, amelyet kifejezetten Minecraft 1.21.1 (Fabric) verzióhoz és korlátozott hardverű ARM mobileszközökhöz fejlesztettünk ki. Fejlett Mixin-alapú bájtkód-injektálást használva agresszív renderelési optimalizációkat kényszerít ki a lehető legmagasabb FPS elérése érdekében PojavLauncher és Zalith Launcher környezetben.

...row-start horizontal=spacedBy(12)
    ...button text="JÁTÉK INDÍTÁSA" event="launch_game" weight=(2)
    ...button-outlined text="FRISSÍTÉSEK" event="check_update" weight=(1)
...row-end

---

...card-start title="📊 TESZTESZKÖZ TELJESÍTMÉNY" shape=medium contentPadding=(14, 10)
- **Eszköz:** Poco X5 5G (Snapdragon 695, 6GB RAM)
- **Elért teljesítmény:** **150-230+ FPS** (Teljesen alap, Vanilla-szerű környezetben, egyéb optimalizációs modok használata nélkül!)
...card-end

---

...card-start title="🚀 KULCSFONTOSSÁGÚ FUNKCIÓK (Újdonságok a v1.1-ben!)" shape=12dp contentPadding=(16, 12)
Az **OptiCore** motor az alábbi modulokat aktiválta a háttérben:

### 🛠 Megjelenítés & Grafika
- **🟢 Okos Chunk Prioritás (SmoothChunk):** Mobilos processzorokra teljesen újraírt chunk-építési logika. Az FPS stabil és zökkenőmentes marad új területek betöltése és generálása közben is.
- **🟢 Állandó Turbo Mód:** Automatikusan kikényszeríti a leggyorsabb grafikai beállításokat (gyors grafika, minimális részecskék) a maximális teljesítményért.
- **🟢 Dinamikus Fényerő Optimalizálás (LightUpdate):** Újraírja a játék fényfrissítési rendszerét, teljesen megszüntetve a mozgás közbeni hirtelen akadásokat (lag spike-okat).
- **🟢 Golyóálló Animáció-fojtás:** Az atlasz-szintű injektálással minden második frame-ben leállítja a távoli animált textúrák (víz, láva, tűz) felesleges frissítését, drasztikusan tehermentesítve a MobileGlues renderelőt.

### 🤖 Logika & Entitások
- **🟢 Agresszív Entity Culling (Lény-kitakarás):** Megakadályozza a 20 blokknál távolabbi entitások és eldobott tárgyak renderelését, rengeteg CPU és GPU ciklust spórolva. A saját karaktered (F5 mód) természetesen látható marad!
- **🟢 Intelligens Memóriakezelés:** Optimalizált maglogika az objektumallokáció minimalizálására, ami drasztikusan csökkenti a Garbage Collector (szemétgyűjtő) okozta mikroszaggatásokat.

### 📱 Mobil Specializáció
- **🟢 Játékon belüli FPS Számláló (HUD):** Egyedi, pixel-stílusú, dinamikus színkódolással ellátott kijelző, amelyet a mobilos láthatósághoz igazítottunk.
- **🟢 ARM / Pojav Optimalizáció:** Kifejezetten olyan mobil környezetekre hangolva, ahol a túlmelegedés és a termikus fojtás (thermal throttling) kritikus tényező.
...card-end

---

...card-start title="⚡ PROFI JVM ARGUMENTUMOK (Zalith)" shape=large
A chunkok akadásmentes betöltése és a G1GC memóriakezelő maximális hatékonysága érdekében másold be ezt a sort a Zalith beállításaiba (4GB RAM ajánlott):

```text
-Xmx4G -Xms4G -XX:+UseG1GC -XX:+ParallelRefProcEnabled -XX:MaxGCPauseMillis=20 -XX:+UnlockExperimentalVMOptions -XX:+DisableExplicitGC -XX:+AlwaysPreTouch -XX:G1NewSizePercent=30 -XX:G1MaxNewSizePercent=40 -XX:G1HeapRegionSize=8M -XX:G1ReservePercent=20 -XX:G1HeapWastePercent=5 -XX:G1MixedGCCountTarget=4 -XX:InitiatingHeapOccupancyPercent=15 -XX:G1MixedGCLiveThresholdPercent=90 -XX:G1RSetUpdatingPauseTimePercent=5 -XX:SurvivorRatio=32 -XX:+PerfDisableSharedMem -XX:MaxTenuringThreshold=1 -Dsun.rbac.logging=false -XX:+UseStringDeduplication

...card-end
...card-start title="🌐 PROJEKT LINKEK ÉS LICENC (EULA)" shape=medium contentPadding=(12)
...column-start vertical=spacedBy(8) horizontal=Center
...row-start horizontal=spacedBy(8)
...button-filled-tonal text="CurseForge" event="url {https://www.curseforge.com/minecraft/mc-mods/optymobileplusz}" weight=(1)
...button-filled-tonal text="Modrinth" event="url {https://modrinth.com/project/mOSzynh9}" weight=(1)
...row-end
...button-text text="Kattints a Hivatalos Licencszerződés (EULA) másolásához!" event="copy {Tulajdonjog: Az OptiMobilePlusz az alkotó (AidStudio) kizárólagos szellemi tulajdona. A mod ZÁRT FORRÁSKÓDÚ (Closed Source). Dekompilálása, visszafejtése, módosítása vagy más projektekbe építése szigorúan TILOS! Terjesztés: A modfájlok (.jar) feltöltése és terjesztése más weboldalakra engedély nélkül tilos. Letöltés kizárólag a hivatalos CurseForge és Modrinth oldalakról lehetséges. Modpack használat: Bármilyen nyilvános vagy privát modpackba szabadon betehető, amennyiben az alkotó neve tisztán fel van tüntetve és a leírás tartalmazza a hivatalos CurseForge/Modrinth linket. Tartalomgyártás: Videókban és közvetítésekben (YouTube, TikTok, Twitch) bemutatható, ha a hivatalos letöltési link szerepel a leírásban. Felelősségkorlátozás: Az alkotó nem vállal felelősséget a használatból eredő esetleges szoftverhibákért vagy teljesítménybeli problémákért.}" width=100%
...column-end
...card-end
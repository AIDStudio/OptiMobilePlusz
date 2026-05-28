[![CurseForge](https://img.shields.io/badge/CurseForge-Download-blue?logo=curseforge&logoColor=white)](https://www.curseforge.com/minecraft/mc-mods/optymobileplusz)

# 📱 OptiMobilePlusz (v1.3)

**OptiMobilePlusz** is a mobile-focused performance mod for **Minecraft 1.21.1 (Fabric)**, optimized for devices running through **PojavLauncher**, **Zalith Launcher 2**, and other ARM-heavy environments. The mod uses Fabric client hooks, runtime optimization modules, and GUI-driven presets to keep FPS stable on weaker hardware.

## 📊 Tested performance profile
- **Target devices:** Snapdragon 695 / ARM-based Android and low-power handheld setups
- **Result:** stable FPS gains through adaptive throttling, chunk handling, entity culling, LOD scaling, and visual simplification
- **Best use:** mobile-first play, low-memory devices, modpacks where FPS stability matters more than max visual fidelity

---

## 🚀 New features and improvements

### 🎛 Adaptive performance engine
- **Adaptive Engine** automatically switches between `EXTREME`, `PERFORMANCE`, `BALANCED`, and `ULTRA` based on real-time FPS.
- **Frame Budget Manager** activates when performance drops, reducing expensive visual load and smoothing gameplay.
- **GPU Profile Manager** detects device limitations and adapts the optimization profile.

### 🧠 Rendering and world optimization
- **Smart Entity Culling** hides distant entities to reduce CPU and GPU cost.
- **LOD Manager** lowers entity distance scaling dynamically during weak FPS periods.
- **Render Throttle** disables unnecessary visual effects, keeps simulation distance controlled, and disables view bobbing.
- **Scene Simplifier** switches graphics behavior to a lighter profile for mobile stability.
- **Texture Streaming Manager** throttles async texture work when performance is under pressure.
- **Particle Limiter** reduces particle load automatically depending on the active performance state.

### 📦 Chunk, audio, and background handling
- **Adaptive Chunk Loading** changes chunk simulation behavior based on motion and performance conditions.
- **Background Tick Throttler** skips heavy background processing when the game loses focus.
- **Sound Budget Manager** reduces non-essential audio load.
- **Thermal Protection** warns when the device is entering a risky thermal/lag zone.

### 📱 Mobile UX improvements
- **Smooth zoom** with the built-in keybind (default `C`) for quick visual inspection.
- **Pixel-style FPS counter** with color-coded performance display.
- **Detailed overlay** shows active budget state, particle multiplier and current runtime performance hints.
- **Preset Manager** supports `battery`, `balanced`, and `competition` profiles for quickly switching optimization behavior.
- **Config GUI** lets players toggle performance features without editing files manually.

---

## 🛠 Installation
1. Install **Fabric Loader** for Minecraft 1.21.1 and ensure **Java 21** is available.
2. Download the latest `-RELEASE.jar`.
3. Put the file into your `mods` folder.
4. Launch Minecraft and use the built-in config screen / in-game performance features.

---

## 🎮 Recommended controls and presets
- **Zoom:** `C`
- **FPS overlay:** enabled by default for mobile visibility
- **Presets:**
  - `battery` → maximum stability, aggressive culling, background throttling, lower particle and sound load
  - `balanced` → safe default for regular play
  - `competition` → lighter performance tuning with telemetry enabled

---

## 📁 Included runtime data
- Performance settings are saved into the Fabric config directory.
- Presets and telemetry snapshots are stored locally for quick recovery and tuning.
- The mod is designed to stay lightweight while adapting to mobile hardware changes during play.

---

## ⚖️ License Agreement (EULA)

**OptiMobilePlusz – End User License Agreement & Terms of Use**

1. **Ownership and Source Code:**
OptiMobilePlusz (hereinafter: "Mod") is the exclusive intellectual property of the creator (**AidStudio**). The Mod is **OPEN SOURCE**. Decompilation, reverse engineering, modification, or incorporating any part of the Mod into other projects is strictly PROHIBITED.

2. **Distribution Restrictions:**
Uploading or distributing the Mod files (.jar) to any other website, hosting service, or social platform without the creator's explicit permission is not allowed. The Mod may only be downloaded through official CurseForge and Modrinth pages.

3. **Modpack Usage:**
The Mod may be freely included in any modpack (public or private) without prior permission, provided that:
- The creator's name and the original name of the Mod are clearly credited.
- A link to the Mod's official CurseForge or Modrinth page is included in the modpack description.

4. **Content Creation:**
The Mod may be featured in videos and live streams (YouTube, Twitch, TikTok, etc.), provided that the official download link is included in the description.

5. **Limitation of Liability:**
The creator is not responsible for any performance issues, software errors, or damages resulting from the use of the Mod.

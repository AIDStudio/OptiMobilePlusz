package com.optimobileplusz.client;

import com.optimobileplusz.client.gui.OptiMobileConfigScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class OptiMobileModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        // Ez a rész tökéletesen működik, mivel a ConfigScreenFactory 
        // a ModMenu saját felülete.
        return parent -> new OptiMobileConfigScreen(parent);
    }
}

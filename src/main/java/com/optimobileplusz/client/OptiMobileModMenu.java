package com.optimobileplusz.client;

import com.optimobileplusz.client.gui.OptiMobileConfigScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class OptiMobileModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> new OptiMobileConfigScreen(parent);
    }
}

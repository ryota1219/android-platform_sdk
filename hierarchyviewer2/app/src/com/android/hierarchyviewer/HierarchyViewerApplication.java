/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.hierarchyviewer;

import com.android.hierarchyviewerlib.ComponentRegistry;
import com.android.hierarchyviewerlib.HierarchyViewerDirector;
import com.android.hierarchyviewerlib.models.DeviceSelectionModel;
import com.android.hierarchyviewerlib.models.PixelPerfectModel;
import com.android.hierarchyviewerlib.models.TreeViewModel;

public class HierarchyViewerApplication {
    public static void main(String[] args) {
        HierarchyViewerDirector director = new HierarchyViewerApplicationDirector();
        ComponentRegistry.setDirector(director);
        ComponentRegistry.setDeviceSelectionModel(new DeviceSelectionModel());
        ComponentRegistry.setPixelPerfectModel(new PixelPerfectModel());
        ComponentRegistry.setTreeViewModel(new TreeViewModel());
        director.initDebugBridge();
        director.startListenForDevices();
        director.populateDeviceSelectionModel();

        UIThread.runUI();
        director.stopListenForDevices();
        director.stopDebugBridge();
        director.terminate();
        System.exit(0);
    }
}

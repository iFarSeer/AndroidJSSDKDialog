/*
 *    Copyright 2016 ifarseer
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.farseer.jssdk.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.farseer.jssdk.JSProcessor;
import com.farseer.jssdk.Processor;
import com.farseer.jssdk.dialog.event.NormalDialogEvent;
import com.farseer.jssdk.internal.JSInvoker;
import com.farseer.tool.JsonTool;
import com.farseer.tool.LogTool;
import com.squareup.otto.Subscribe;

/**
 * class description
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 16/6/27
 */

@Processor(name = "Dialog")
public class DialogProcessor extends JSProcessor {
    private MaterialDialog materialDialog = null;

    public DialogProcessor(Context context, JSInvoker jsInvoker, String name) {
        super(context, jsInvoker, name);
    }

    @Subscribe
    public void processNormalDialogEvent(final NormalDialogEvent event) {
        NormalDialogEvent.Request data = event.getRequest();
        hideDialog();
        materialDialog = new MaterialDialog.Builder(getContext())
                .title(data.getTitle())
                .content(data.getContent())
                .positiveText(data.getPositiveText())
                .negativeText(data.getNegativeText())
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        LogTool.debug("onPositive");
                        NormalDialogEvent.Response result = new NormalDialogEvent.Response();
                        result.setMessage("sure");
                        result.setResult(1);
                        getJsInvoker().onJsInvoke(event.getCallback(JsonTool.toJsonString(result)));
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        LogTool.debug("onNegative");
                        NormalDialogEvent.Response result = new NormalDialogEvent.Response();
                        result.setMessage("cancel");
                        result.setResult(0);
                        getJsInvoker().onJsInvoke(event.getCallback(JsonTool.toJsonString(result)));
                    }
                })
                .build();
        materialDialog.show();
    }

    private void hideDialog() {
        if (materialDialog != null) {
            if (materialDialog.isShowing()) {
                materialDialog.dismiss();
            }
            materialDialog = null;
        }
    }
}

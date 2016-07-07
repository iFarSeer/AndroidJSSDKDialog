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

package com.farseer.jssdk.dialog.event;

import android.text.TextUtils;
import com.farseer.jssdk.JSEvent;
import com.farseer.tool.JsonTool;
import com.farseer.tool.LogTool;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

/**
 * class description
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 16/6/27
 */
public class NormalDialogEvent extends JSEvent {

    private Request request;

    public NormalDialogEvent(String module, String function) {
        super(module, function);
    }


    public Request getRequest() {
        return request;
    }


    @Override
    public void processData(String data) {
        log(data);
        request = JsonTool.fromJsonString(data, new TypeToken<Request>() {}.getType());
        if (request == null || !request.check()) {
            LogTool.error(String.format("normalDialog 's params of the module named %s are not support", getModule()));
        }
    }

    public static class Request {
        @SerializedName("title")
        private String title;
        @SerializedName("content")
        private String content;
        @SerializedName("positiveText")
        private String positiveText;
        @SerializedName("negativeText")
        private String negativeText;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPositiveText() {
            return positiveText;
        }

        public void setPositiveText(String positiveText) {
            this.positiveText = positiveText;
        }

        public String getNegativeText() {
            return negativeText;
        }

        public void setNegativeText(String negativeText) {
            this.negativeText = negativeText;
        }

        public boolean check() {
            if (!TextUtils.isEmpty(title)
                    && !TextUtils.isEmpty(content)
                    && !TextUtils.isEmpty(positiveText)
                    && !TextUtils.isEmpty(negativeText)) {
                return true;
            }
            return false;
        }
    }

    public static class Response {
        @SerializedName("result")
        private int result;
        @SerializedName("message")
        private String message;

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}

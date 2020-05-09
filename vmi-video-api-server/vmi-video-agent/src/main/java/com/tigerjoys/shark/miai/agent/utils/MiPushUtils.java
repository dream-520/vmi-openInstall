package com.tigerjoys.shark.miai.agent.utils;

import com.alibaba.fastjson.JSONObject;
import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;

public class MiPushUtils {

    private final static int PASS_THROUGH_MESSAGE = 0;// 1表示透传消息
    private final static int NOTIFICATION_MESSAGE = 0;// 0表示通知栏消息
    private final static int PASS_THROUGH = NOTIFICATION_MESSAGE;// 1表示透传消息，0表示通知栏消息

    private final static int DEFAULT_ALL = -1;
    private final static int DEFAULT_SOUND = 1; // 使用默认提示音提
    private final static int DEFAULT_VIBRATE = 2; // 使用默认震动提示
    private final static int DEFAULT_LIGHTS = 4; // 使用默认led灯光提示
    private final static int NOTIFY_TYPE = DEFAULT_ALL;

    private String ANDROID_APP_SECRET = "";
    private String IOS_APP_SECRET = "";
    private String ANDROID_PACKAGE_NAME = "";
    private static int DEVELOPMENT_MODE = 1; // 1=正式环境/0=测试

    public final static int TYPE_ANDROID = 0;
    public final static int TYPE_IOS = 1;

    /**
     * 构造函数
     */
    public MiPushUtils(String androidAppSecret, String androidPackageName, String iosAppSecret, int developmentMode) {
        this.ANDROID_APP_SECRET = androidAppSecret;
        this.ANDROID_PACKAGE_NAME = androidPackageName;
        this.IOS_APP_SECRET = iosAppSecret;
        MiPushUtils.DEVELOPMENT_MODE = developmentMode;
    }

    /**
     * 调用小米推送
     */
    public static void reStartPush(int deviceType) {
        // 如果为测试环境
        if (DEVELOPMENT_MODE == 0) {
            // 测试环境只提供对IOS支持，不支持Android
            Constants.useSandbox();
            if (deviceType == TYPE_ANDROID) {
                Constants.useOfficial();
            }
        } else {
            // 正式环境
            Constants.useOfficial();
        }
    }

    /**
     * 构建android推送信息
     *
     * @param title
     * @param content
     * @param jsonObjectPayload
     * @param timeToSend
     * @return
     */
    private Message buildMessage2Android(String title, String content, JSONObject jsonObjectPayload, long timeToSend)
            throws Exception {
        Message message = new Message.Builder().title(title).description(content)
                .payload(jsonObjectPayload.toJSONString()).restrictedPackageName(ANDROID_PACKAGE_NAME)// 设置包名
                .passThrough(PASS_THROUGH) // 消息使用透传方式
                .notifyType(NOTIFY_TYPE) // 使用默认提示音提示
                .enableFlowControl(true) // 控制消息是否需要进行平缓发送
                .timeToSend(timeToSend) // 定时推送时间
                .build();
        return message;
    }

    /**
     * 构建ios推送信息
     *
     * @param content
     * @param jsonObjectPayload
     * @param timeToSend
     * @return
     */
    private Message buildMessage2IOS(String content, JSONObject jsonObjectPayload, long timeToSend) throws Exception {
        Message message = new Message.IOSBuilder().description(content).badge(1) // 数字角标
                .extra("payload", jsonObjectPayload.toJSONString()).timeToSend(timeToSend).build();
        return message;
    }

    /**
     * 构建发送信息
     *
     * @param title
     * @param content
     * @param jsonObjectPayload
     * @param deviceType
     * @param timeToSend
     * @return Message
     */
    private Message buildMessage(String title, String content, JSONObject jsonObjectPayload, int deviceType,
            long timeToSend) throws Exception {
        Message message = null;
        if (deviceType == TYPE_ANDROID) {
            message = buildMessage2Android(title, content, jsonObjectPayload, timeToSend);
        } else if (deviceType == TYPE_IOS) {
            message = buildMessage2IOS(content, jsonObjectPayload, timeToSend);
        }
        return message;
    }

    /**
     * 向所有设备发送推送信息
     *
     * @param title
     * @param content
     * @param jsonObjectPayload
     * @param deviceType
     * @param timeToSend
     * @throws Exception
     */
    public Result sendBroadcastAll(String title, String content, JSONObject jsonObjectPayload, int deviceType,
            long timeToSend) throws Exception {
        reStartPush(deviceType);// 准备小米推送

        Sender sender = null;
        if (deviceType == TYPE_ANDROID) {
            sender = new Sender(ANDROID_APP_SECRET); // 需要根据appSecert来发送
        } else if (deviceType == TYPE_IOS) {
            sender = new Sender(IOS_APP_SECRET); // 需要根据appSecert来发送
        }
        Message message = buildMessage(title, content, jsonObjectPayload, deviceType, timeToSend);
        Result result = sender.broadcastAll(message, 0);// 推送消息给所有设备，不重试

        return result;
    }

    /**
     * 根据regid发送一条短信
     *
     * @param title
     * @param content
     * @param jsonObjectPayload
     * @param regId
     * @param deviceType
     * @throws Exception
     */
    public Result sendMessageToRegId(String title, String content, JSONObject jsonObjectPayload, String regId, int deviceType,
            long timeToSend) throws Exception {
        reStartPush(deviceType);// 准备小米推送

        Sender sender = null;
        if (deviceType == TYPE_ANDROID) {
            sender = new Sender(ANDROID_APP_SECRET); // 需要根据appSecert来发送
        } else if (deviceType == TYPE_IOS) {
            sender = new Sender(IOS_APP_SECRET); // 需要根据appSecert来发送
        }
        Message message = buildMessage(title, content, jsonObjectPayload, deviceType, timeToSend);
        Result result = sender.send(message, regId, 0); // 根据regID，发送消息到指定设备上，不重试。
        return result;
    }

    /**
     * 根据alias发送一条短信
     *
     * @param title
     * @param content
     * @param jsonObjectPayload
     * @param userMobile
     * @param deviceType
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private Result sendMessageToAlias(String title, String content, JSONObject jsonObjectPayload, String userMobile,
            int deviceType, long timeToSend) throws Exception{
        reStartPush(deviceType);// 准备小米推送

        Sender sender = null;
        if (deviceType == TYPE_ANDROID) {
            sender = new Sender(ANDROID_APP_SECRET); // 需要根据appSecert来发送
        } else if (deviceType == TYPE_IOS) {
            sender = new Sender(IOS_APP_SECRET); // 需要根据appSecert来发送
        }
        Message message = buildMessage(title, content, jsonObjectPayload, deviceType, timeToSend);
        Result result = sender.sendToAlias(message, userMobile, 0); // 根据alias，发送消息到指定设备上，不重试。
        return result;
    }
}


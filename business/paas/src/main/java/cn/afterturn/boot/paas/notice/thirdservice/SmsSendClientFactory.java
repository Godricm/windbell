package cn.afterturn.boot.paas.notice.thirdservice;

import cn.afterturn.boot.paas.notice.model.ImChannelEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 创建短信发送客户端
 *
 * @author jueyue on 19-11-12.
 */
@Component
public class SmsSendClientFactory {

    @Resource(name = "smsSendClientOfAliYun")
    private ISmsSendClient smsSendClientOfAliYun;

    public ISmsSendClient get(String channel) {
        ImChannelEnum channelEnum = ImChannelEnum.to(channel);
        switch (channelEnum) {
            case ALIYUN:
                return smsSendClientOfAliYun;
        }
        return smsSendClientOfAliYun;
    }
}
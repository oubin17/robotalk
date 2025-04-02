package com.odk.ai.infra;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;

import java.util.Arrays;

/**
 * Main
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/4/2
 */
public class Main {
    public static GenerationResult callWithMessage() throws ApiException, NoApiKeyException, InputRequiredException {
        Generation gen = new Generation();
        Message userMsg1 = Message.builder()
                .role(Role.USER.getValue())
                .content("你好")
                .build();
        Message AssistantMsg = Message.builder()
                .role(Role.ASSISTANT.getValue())
                .content("你好！很高兴见到你，有什么我可以帮忙的吗？")
                .build();
        Message UserMsg2 = Message.builder()
                .role(Role.USER.getValue())
                .content("你是谁")
                .build();
        GenerationParam param = GenerationParam.builder()
                // 若没有配置环境变量，请用百炼API Key将下行替换为：.apiKey("sk-xxx")
                .apiKey("sk-dc1b9f5a289847d1a9ca2de7307ace85")
                .model("deepseek-r1")
                .messages(Arrays.asList(userMsg1,AssistantMsg,UserMsg2))
                // 不可以设置为"text"
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .build();
        return gen.call(param);
    }
    public static void main(String[] args) {
        try {
            GenerationResult result = callWithMessage();
            System.out.println("思考过程：");
            System.out.println(result.getOutput().getChoices().get(0).getMessage().getReasoningContent());
            System.out.println("回复内容：");
            System.out.println(result.getOutput().getChoices().get(0).getMessage().getContent());
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            // 使用日志框架记录异常信息
            System.err.println("An error occurred while calling the generation service: " + e.getMessage());
        }
        System.exit(0);
    }
}

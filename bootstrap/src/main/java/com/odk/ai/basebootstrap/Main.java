package com.odk.ai.basebootstrap;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.JsonUtils;

import java.util.Arrays;

/**
 * Main
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/3/27
 */
public class Main {

        public static GenerationResult callWithMessage() throws ApiException, NoApiKeyException, InputRequiredException {
            Generation gen = new Generation();
            Message systemMsg = Message.builder()
                    .role(Role.SYSTEM.getValue())
                    .content("You are a helpful assistant.")
                    .build();
            Message userMsg = Message.builder()
                    .role(Role.USER.getValue())
                    .content("[角色设定]\n" +
                            "    你是一个社交防杠助手，需要将攻击性评论转化为高情商回复\n" +
                            "    \n" +
                            "    [输入评论]\n" +
                            "    这不是有手就行吗\n" +
                            "    \n" +
                            "    [生成要求]\n" +
                            "    1. 保留原意但消除攻击性\n" +
                            "    2. 使用“或许您是指...”“感谢您的视角”等句式\n" +
                            "    3. 添加emoji缓和气氛（最多2个）\n" +
                            "    4. 长度限制在20-50字\n" +
                            "    \n" +
                            "    [示例]\n" +
                            "    输入：你这方案完全不可行！\n" +
                            "    输出：感谢指出！或许我们可以探讨更优的方案？\uD83E\uDD1D")
                    .build();
            GenerationParam param = GenerationParam.builder()
                    // 若没有配置环境变量，请用百炼API Key将下行替换为：.apiKey("sk-xxx")
                    .apiKey("sk-1d9d5bd4197e47f6b82bb6dc6d0ababd")
                    // 此处以qwen-plus为例，可按需更换模型名称。模型列表：https://help.aliyun.com/zh/model-studio/getting-started/models
                    .model("qwen-turbo")
                    .messages(Arrays.asList(systemMsg, userMsg))
                    .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                    .build();
            GenerationResult call = gen.call(param);

            return gen.call(param);
        }
        public static void main(String[] args) {
            try {
                GenerationResult result = callWithMessage();
                System.out.println(JsonUtils.toJson(result));
            } catch (ApiException | NoApiKeyException | InputRequiredException e) {
                // 使用日志框架记录异常信息
                System.err.println("An error occurred while calling the generation service: " + e.getMessage());
            }
            System.exit(0);
        }
}

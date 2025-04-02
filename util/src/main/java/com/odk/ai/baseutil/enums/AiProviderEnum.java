package com.odk.ai.baseutil.enums;

import com.odk.base.enums.IEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * AiProviderEnum
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/4/1
 */
public enum AiProviderEnum implements IEnum {

    QWEN("qwen-plus", "千问模型"),

    DEEPSEEK("deepseek-r1", "DEEPSEEK_R1"),

    OPENAI("OPENAI", "OpenAI"),

    ;

    private final String code;

    private final String description;

    AiProviderEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static AiProviderEnum getByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (AiProviderEnum providerEnum : values()) {
            if (providerEnum.code.equals(code)) {
                return providerEnum;
            }
        }
        return null;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}

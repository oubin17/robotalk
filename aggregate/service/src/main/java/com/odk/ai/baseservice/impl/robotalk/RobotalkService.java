package com.odk.ai.baseservice.impl.robotalk;

import com.odk.ai.baseapi.inter.robotalk.RobotalkApi;
import com.odk.ai.basemanager.deal.robotalk.RobotalkManager;
import com.odk.ai.baseservice.template.AbstractApiImpl;
import com.odk.ai.baseutil.enums.AiProviderEnum;
import com.odk.ai.baseutil.enums.BizScene;
import com.odk.ai.baseutil.request.role.RobotalkAskRequest;
import com.odk.base.exception.AssertUtil;
import com.odk.base.exception.BizErrorCode;
import com.odk.base.vo.response.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RobotalkService
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/3/27
 */
@Service
public class RobotalkService extends AbstractApiImpl implements RobotalkApi {

    private RobotalkManager robotalkManager;

    @Override
    public ServiceResponse<String> callAi(RobotalkAskRequest robotalkAskRequest) {
        return super.bizProcess(BizScene.AI_CALL, robotalkAskRequest, new ApiCallBack<String, String>() {
            @Override
            protected void checkParams(Object request) {
                AssertUtil.notNull(robotalkAskRequest, BizErrorCode.PARAM_ILLEGAL, "输入不为空");
                AssertUtil.notNull(robotalkAskRequest.getContent(), BizErrorCode.PARAM_ILLEGAL, "输入不为空");
                AssertUtil.isTrue(robotalkAskRequest.getContent().length() < 100, BizErrorCode.PARAM_ILLEGAL, "输入内容太长");
                AssertUtil.notNull(AiProviderEnum.getByCode(robotalkAskRequest.getLlm()), BizErrorCode.PARAM_ILLEGAL, "不支持的模型");

            }

            @Override
            protected String doProcess(Object args) {
                return robotalkManager.deal(robotalkAskRequest.getContent(), robotalkAskRequest.getLlm());
            }

            @Override
            protected String convertResult(String apiResult) {
                return apiResult;
            }

        });
    }

    @Autowired
    public void setRobotalkManager(RobotalkManager robotalkManager) {
        this.robotalkManager = robotalkManager;
    }
}

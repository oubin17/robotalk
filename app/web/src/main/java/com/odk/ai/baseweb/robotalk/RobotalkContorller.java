package com.odk.ai.baseweb.robotalk;

import com.odk.ai.baseapi.inter.robotalk.RobotalkApi;
import com.odk.ai.baseutil.request.role.RobotalkAskRequest;
import com.odk.base.vo.response.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * RobotalkContorller
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/3/27
 */
@CrossOrigin
@RestController
@RequestMapping("/ask")
public class RobotalkContorller {


    private RobotalkApi robotalkApi;

    @PostMapping("/turbo")
    ServiceResponse<String> callAi(@RequestBody RobotalkAskRequest robotalkAskRequest) {
        return robotalkApi.callAi(robotalkAskRequest);
    }

    @Autowired
    public void setRobotalkApi(RobotalkApi robotalkApi) {
        this.robotalkApi = robotalkApi;
    }
}

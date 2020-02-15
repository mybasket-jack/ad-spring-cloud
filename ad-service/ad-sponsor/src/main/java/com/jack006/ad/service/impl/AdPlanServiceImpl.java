package com.jack006.ad.service.impl;

import com.jack006.ad.constant.CommonStatus;
import com.jack006.ad.constant.Constants;
import com.jack006.ad.dao.AdPlanRepository;
import com.jack006.ad.dao.AdUserRepository;
import com.jack006.ad.entity.AdPlan;
import com.jack006.ad.entity.AdUser;
import com.jack006.ad.exception.AdException;
import com.jack006.ad.service.IAdPlanService;
import com.jack006.ad.utils.CommonUtils;
import com.jack006.ad.vo.AdPlanGetRequest;
import com.jack006.ad.vo.AdPlanRequest;
import com.jack006.ad.vo.AdPlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 推广计划的接口实现
 *
 */
@Service
public class AdPlanServiceImpl implements IAdPlanService {

    private final AdUserRepository userRepository;
    private final AdPlanRepository planRepository;

    @Autowired
    public AdPlanServiceImpl(AdUserRepository userRepository, AdPlanRepository planRepository) {
        this.userRepository = userRepository;
        this.planRepository = planRepository;
    }

    @Override
    @Transactional
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {
        if (!request.createValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        // 确保关联的 User 是存在的
        Optional<AdUser> adUser = userRepository.findById(request.getId());
        if (!adUser.isPresent()) {
           throw new AdException(Constants.ErrorMsg.CAN_NOT_FOUND_RECORD);
        }

        AdPlan oldPlan = planRepository.findByUserIdAndPlanName(request.getUserId(), request.getPlanName());
        if (oldPlan != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_PLAN_ERROR);
        }
        AdPlan newAdPlan = planRepository.save(
                new AdPlan(request.getUserId(), request.getPlanName(),
                        CommonUtils.parseString2Date(request.getStartDate()),
                        CommonUtils.parseString2Date(request.getEndDate())
                )
        );
        return new AdPlanResponse(newAdPlan.getId(), newAdPlan.getPlanName());
    }

    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException {

        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        return planRepository.findAllByIdInAndUserId(
                request.getIds(), request.getUserId()
        );
    }

    @Override
    @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {
        if (!request.updateValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan oldPlan = planRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if (oldPlan == null ) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FOUND_RECORD);
        }

        if (request.getPlanName() != null ) {
            oldPlan.setPlanName(request.getPlanName());
        }
        if (request.getStartDate() != null) {
            oldPlan.setStartDate(CommonUtils.parseString2Date(request.getStartDate()));
        }
        if (request.getEndDate() != null) {
            oldPlan.setEndDate(CommonUtils.parseString2Date(request.getEndDate()));
        }
        oldPlan.setUpdateTime(new Date());
        AdPlan newPlan = planRepository.save(oldPlan);
        return new AdPlanResponse(newPlan.getId(), newPlan.getPlanName());
    }

    @Override
    @Transactional
    public void deleteAdPlan(AdPlanRequest request) throws AdException {
        if (!request.deleteValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan oldPlan = planRepository.findByIdAndUserId(
            request.getId(), request.getUserId()
        );
        if (oldPlan == null ) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FOUND_RECORD);
        }
        oldPlan.setPlanStatus(CommonStatus.INVALID.getStatus());
        oldPlan.setUpdateTime(new Date());
        planRepository.save(oldPlan);
    }
}

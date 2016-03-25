package com.goldbao.bankroll.dao.user;

import com.goldbao.bankroll.model.user.PhoneValidateCode;
import com.goldbao.bankroll.model.enums.EnumValidateCodePurpose;

/**
 * 手机验证码
 */
public interface PhoneValidateCodeDao {
    /**
     * 根据phone获取验证码记录
     * @param phone
     * @param purpose
     * @return
     */
    PhoneValidateCode getLastValidateCodeByPhone(String phone, EnumValidateCodePurpose purpose);

    /**
     * 添加一条验证码记录
     * @param validateCode
     * @return
     */
    PhoneValidateCode addValidateCode(PhoneValidateCode validateCode);

    Integer updateToUsed(Long id);

    PhoneValidateCode getValidateCodeById(Long validateCodeId);
}

package com.yunxin.cb.system.service;

import com.yunxin.cb.system.MobileOSType;
import com.yunxin.cb.system.vo.AppCheckUpdate;

public interface IProfileService {
    AppCheckUpdate getAppCheckUpdate(MobileOSType mobileOSType);
}

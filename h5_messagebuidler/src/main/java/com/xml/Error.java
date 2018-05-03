package com.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by liweiping on 2018/4/12.
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Error {
    @XmlAttribute()
    private String tipsId;
    @XmlAttribute()
    private String desc;

    public String getTipsId() {
        return tipsId;
    }

    public void setTipsId(String tipsId) {
        this.tipsId = tipsId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

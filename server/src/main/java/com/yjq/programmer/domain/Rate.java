package com.yjq.programmer.domain;

import java.util.Date;

public class Rate {
    private String id;

    private String userId;

    private String warnId;

    private Integer type;

    private Integer score;

    private Date createTime;

    private String content;

    private Integer nowScore;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWarnId() {
        return warnId;
    }

    public void setWarnId(String warnId) {
        this.warnId = warnId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNowScore() {
        return nowScore;
    }

    public void setNowScore(Integer nowScore) {
        this.nowScore = nowScore;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", warnId=").append(warnId);
        sb.append(", type=").append(type);
        sb.append(", score=").append(score);
        sb.append(", createTime=").append(createTime);
        sb.append(", content=").append(content);
        sb.append(", nowScore=").append(nowScore);
        sb.append("]");
        return sb.toString();
    }
}
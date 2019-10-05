package com.rea.tours.domain;

import com.rea.tours.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Orders implements Serializable {
    private String id;

    private String orderNum;

    private Date orderTime;
    private String orderTimeStr;

    private Integer peopleCount;

    private String orderDesc;

    private Integer payType;
    private String payTypeStr;

    private Integer orderStatus;
    private String orderStatusStr;

    private String productID;
    private Product product;
    private List<Traveller> travellers;

    private String memberid;
    private Member member;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc == null ? null : orderDesc.trim();
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID == null ? null : productID.trim();
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid == null ? null : memberid.trim();
    }

    public String getOrderTimeStr()
    {
        if(orderTime!=null)
            orderTimeStr= DateUtils.date2String(orderTime,"yyyy-MM-dd HH:mm");
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr)
    {
        this.orderTimeStr = orderTimeStr;
    }

    public String getPayTypeStr()
    {
        if(payType!=null)
        {
            if(payType==0)
                payTypeStr="支付宝";
            if(payType==1)
                payTypeStr="微信";
            if(payType==2)
                payTypeStr="其他";
        }
        return payTypeStr;
    }

    public void setPayTypeStr(String payTypeStr)
    {
        this.payTypeStr = payTypeStr;
    }

    public String getOrderStatusStr()
    {
        if(orderStatus!=null)
        {
            if(orderStatus==0)
                orderStatusStr="未支付";
            if(orderStatus==1)
                orderStatusStr="已支付";
        }
        return orderStatusStr;
    }

    public void setOrderStatusStr(String orderStatusStr)
    {
        this.orderStatusStr = orderStatusStr;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public List<Traveller> getTravellers()
    {
        return travellers;
    }

    public void setTravellers(List<Traveller> travellers)
    {
        this.travellers = travellers;
    }

    public Member getMember()
    {
        return member;
    }

    public void setMember(Member member)
    {
        this.member = member;
    }
}
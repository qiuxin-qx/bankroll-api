<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接口</title>
</head>
<body>
<h3>1. 用户登录/注册</h3>
<p>登录</p>
<p>${url}/user/login?username=aaa&password=1111111</p>

<p>注册1：获取手机校验码</p>
<p>${url}/user/getPhoneValidateCode?phone=13916297656</p>

<p>注册2：校验手机校验码</p>
<p>${url}/user/validatePhoneCode?phone=13916297656&validateCode=xxx</p>

<p>注册3：校验用户名</p>
<p>${url}/user/checkUserName?username=aaa</p>

<p>注册4：注册</p>
<p>${url}/user/register?username=aaa&phone=13916297656&password=1111111&repassword=1111111&ip=</p>

<p>提交实名认证申请</p>
<p>${url}/user/applyReal?token=&realName=aaa&cardId=xx&cardIdPhoto1=13916297656&cardIdPhoto2=1111111&cardIdPhoto3=1111111</p>

<h3>2. 交易</h3>
<p>充值</p>
<p>${url}/trade/recharge?token=xxx&amount=12.00&bankId=700&returnUrl=http://xxx&remark=xxxx&productName=xxx</p>
<p>获取某笔交易记录</p>
<p>${url}/trade/getTradeRecord?token=xxx&orderNo=111111</p>
<p>获取交易记录</p>
<p>${url}/trade/getTradeRecordList?token=xxx&tradeType=0&index=1&size=10</p>

<h3>3. 用户账户</h3>
<p>用户账户信息</p>
<p>${url}/userfunds/getUserFund?token=xxx</p>
<p>用户账户交易明细（分页）</p>
<p>${url}/userfunds/getUserFundLog?token=xxx&tradeType=0&index=1&size=10</p>

<h3>4. 配资</h3>
<p>用户申请列表</p>
<p>${url}/bankroll/getApplyList?token=xxx&unit=-1&status=-1&fundStat=1&index=1&size=10</p>
<p>用户配资申请明细</p>
<p>${url}/bankroll/getBankrollApplyById?token=xxx&applyId=1</p>

<p>用户配资列表</p>
<p>${url}/bankroll/getBankrollRecordList?token=xxx&unit=-1&status=-1&index=1&size=10</p>
<p>用户配资明细</p>
<p>${url}/bankroll/getBankrollRecordById?token=xxx&recordId=1</p>

<p>用户配资homs详情</p>
<p>${url}/bankroll/getBankrollHomsInfo?token=xxx&recordId=1</p>

<p>用户配资申请列表</p>
<p>${url}/bankroll/getAllApplyList?index=1&size=10</p>
<p>用户配资申请统计</p>
<p>${url}/bankroll/getBankrollApplyStat</p>



<p>==================================</p>
<h3>用户登录（后端）</h3>
<p>${url}/admin/user/login?username=aaa&password=111111</p>

<h3>配资管理</h3>
<p>获取配资审核记录</p>
<p>${url}/admin/bankroll/getApplyList?token=&unit=-1&status=-1&fundStat=0&index=1&size=10</p>
<p>获取配资申请详情</p>
<p>${url}/admin/bankroll/getBankrollApplyById?token=&applyId=1</p>
<p>获取配资列表（分页）</p>
<p>${url}/admin/bankroll/getBankrollRecordList?token=&unit=-1&status=-1&index=1&size=10</p>
<p>获取配资详情</p>
<p>${url}/admin/bankroll/getBankrollRecordById?token=&recordId=1</p>

<p>获取有效的投资日期范围</p>
<p>${url}/admin/bankroll/getRangeTradingDays?token=&date=2015-01-01&days=100</p>

<p>分配homs帐号</p>
<p>${url}/admin/bankroll/allocateHoms?token=&recordId=1&startDate=2015-01-01&endDate=2015-01-01&homsOperator=xxx&homsPwd=xxx&orgId=xx</p>
<p>获取某配资homs详情</p>
<p>${url}/admin/bankroll/getBankrollHomsInfo?token=&recordId=1</p>
<p>获取某配资homs历史记录</p>
<p>${url}/admin/bankroll/getBankrollHomsInfoLog?token=&recordId=1</p>

<p>后台充值（转账）</p>
<p>${url}//admin/trade/bgRecharge?token=&userId=111&money=100.00</p>

<p>获取某笔交易记录</p>
<p>${url}/admin/trade/getTradeRecord?token=xxx&orderNo=111111</p>
<p>获取交易记录</p>
<p>${url}/admin/trade/getTradeRecordList?token=xxx&tradeType=0&userId=&index=1&size=10</p>



<h3>配资审核(通过)</h3>
<p>${url}/admin/bankroll/auditBankrollApply?token=xxx&applyId=1&orgId=1</p>
<h3>配资结束（只修改状态）</h3>
<p>${url}/admin/bankroll/updateBankrollRecordToFinish?token=xxx&recordId=1</p>
<h3>配资结束（结算完成）</h3>
<p>${url}/admin/bankroll/updateBankrollRecordToRepayment?token=xxx&repaymentMoney=xxxxxx&recordId=1</p>



<h3>网站用户管理</h3>
<p>获取用户列表</p>
<p>${url}/admin/user/getUserList?token=xxx&username=xx&index=1&size=10</p>
<p>获取用户详情</p>
<p>${url}/admin/user/getUserById?token=xxx&userId=1</p>

<p>获取用户申请实名认证列表</p>
<p>${url}/admin/user/getUserRealApplyList?token=xxx&status=-1&index=1&size=10</p>
<p>获取用户申请实名认证列表</p>
<p>${url}/admin/user/getUserRealApplyList?token=xxx&status=-1&index=1&size=10</p>
<p>获取用户申请实名认证详情</p>
<p>${url}/admin/user/getUserRealApplyById?token=xxx&id=1</p>

</body>
</html>
package com.goldbao.homs.result;

/**
 * homs数据字典
 */
public class HomsDict {

    /**
     * 交易市场
     */
    public enum ExchangeType {
        /**
         * 上交所
         */
        SHANG_JIAO_SUO("1"),
        /**
         * 深交所
         */
        SHEN_JIAO_SUO("2"),
        /**
         * 上期所
         */
        SHANG_QI_SUO("3"),
        /**
         * 郑商所
         */
        ZHENG_SHANG_SUO("4"),
        /**
         * 中金所
         */
        ZHONG_JIN_SUO("7"),
        /**
         * 大商所
         */
        DA_SHANG_SUO("9");

        private String flag;


        ExchangeType(String flag) {
            this.flag = flag;
        }

        public String getFlag() {
            return flag;
        }
    }

    /**
     * 委托方向
     */
    public enum EntrustDirection {
        /**
         * 股票买入
         */
        STOCK_BUY("1"),
        /**
         * 股票卖出
         */
        STOCK_SELL("2"),
        /**
         * 债券买入
         */
        BOND_BUY("3"),
        /**
         * 债券卖出
         */
        BOND_SELL("4"),
        /**
         * 融资回购
         */
        FINANCING_REPURCHASE("5"),
        /**
         * 融券回购
         */
        SECURITIES_REPURCHASE("6"),

        /**
         * 买入开仓
         */
        BID_OPENING("V"),

        /**
         * 卖出平仓
         */
        SELL_OPEN("W"),

        /**
         * 卖出开仓
         */
        SELL_OPENING("X"),

        /**
         * 买入平仓
         */
        BID_OPEN("Y");
        // TODO 还有更多委托方向定义 ....

        private String flag;

        EntrustDirection(String flag) {
            this.flag = flag;
        }

        public String getFlag() {
            return flag;
        }
    }

    /**
     * 价格类型
     */
    public enum AmpriceType {

        /**
         * 限价【上交所，深交所】
         */
        LIMIT_PRICE("0");

        // TODO 还有更多的价格类型定义 ....

        private String flag;

        AmpriceType(String flag) {
            this.flag = flag;
        }

        public String getFlag() {
            return flag;
        }
    }
}

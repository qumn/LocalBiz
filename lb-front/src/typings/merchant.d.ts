declare namespace LB {
	interface CreateMerchant {
		name: string
		desc?: string
		introImg: String
		phone: string
		geom: {
			x: number
			y: number
		}
		geomDesc?: string
	}

	interface Merchant {
		mid: number
		owner: number
		name: string
		desc?: string
		introImag: String
		phone: string
		geom: {
			x: number
			y: number
		}
		geomDesc?: string
	}

	interface Category {
		catId: number
		mid: number
		name: string
		priority: number
		commodities: Commodity[]
	}

	interface Commodity {
		cid: number
		mid: number
		img: string
		name: string
		desc: string
		specifications: Specification[]
	}

	interface Specification {
		sid: number
		price: number
		attributes: SpecificationAbt[]
	}
	interface SpecificationAbt {
		aid: number
		key: string
		value: string
	}

  type OrderStatus = keyof typeof import('@/enum').EnumOrderStatus;

	interface Order {
		oid: number
		/**
		 * 用户id
		 */
		uid: number

		mid: number
		/**
		 * 订单总金额
		 */
		totalAmount: number
		/**
		 * 订单状态
		 */
		status: OrderStatus
		/**
		 * 创建时间
		 */
		createTime: Date
		/**
		 * 更新时间
		 */
		updateTime: Date

		items: OrderItem[]
	}

	interface OrderItem {
    /**
     * order item id
     */
    oiid: number,
    /**
     * order id
     */
    oid: number,
    /**
     * specification id
     */
    sid: number,
    /**
     * 商品数量
     */
    count: number,
    /**
     * 预约时间(physical类型商品为null)
     */
    appointmentTime: Date,

    commodity: Commodity,
    specification: Specification,
	}
}

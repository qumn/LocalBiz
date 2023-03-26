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
    catId: number,
    mid: number,
    name: string,
    priority: number,
    commodities: Commodity[],
  }

  interface Commodity {
    cid: number,
    mid: number,
    img: string,
    name: string,
    desc: string
    specifications: Specification[]
  }
  
  interface Specification {
    sid: number,
    price: number,
    attributes: SpecificationAbt[]
  }
  interface SpecificationAbt {
    aid: number,
    key: string,
    value: string
  }

}

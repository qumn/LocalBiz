import { lbManagement } from '../request'

export function createMerchant<T extends LB.CreateMerchant>(merchant: T) {
	return lbManagement.post('/merchant', merchant)
}

export function getOwnMerchants() {
	return lbManagement.get<LB.Merchant[]>('/merchant/own')
}

/**
 * get category list
 *
 * @param {number} mid - merchant id
 * @returns
 */
export function fetchCategories(mid: number) {
	return lbManagement.get<LB.Category[]>(`/category/list/${mid}`)
}

/**
 * get commodity by category id
 *
 * @param {number} catId - category id
 */
export function fetchCommodities(catId: number) {
	return lbManagement.get<LB.Commodity[]>(`/commodity/list?catId=${catId}`)
}

/**
 * get commodity by commodity id
 *
 * @param {number} cid - commodity id
 */
export function fetchCommodity(cid: number) {
	return lbManagement.get<LB.Commodity[]>(`/commodity/${cid}`)
}

export function putCommodity(commodity: LB.Commodity) {
	return lbManagement.put('/commodity', commodity)
}

export function postCommodity(commodity: LB.Commodity) {
	return lbManagement.post('/commodity', commodity)
}

export function fetchOrders(mid: number) {
	return lbManagement.get<LB.Order[]>(`/order/list/${mid}`)
}

export function fetchOrderDetail(oid: number) {
	return lbManagement.get<LB.Order>(`/order/${oid}`)
}

export function acceptOrder(oid: number) {
	return lbManagement.put(`/order/accept/${oid}`)
}

export function refuseOrder(oid: number) {
	return lbManagement.put(`/order/refuse/${oid}`)
}

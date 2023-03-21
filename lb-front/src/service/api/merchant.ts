import { lbManagement } from "../request";

interface CreateMerchant {
	name: string;
	desc?: string;
	introImg: String;
	phone: string;
	geom: {
		x: number;
		y: number;
	};
	geomDesc?: string;
}

interface Merchant {
	mid: number;
	owner: number;
	name: string;
	desc?: string;
	introImag: String;
	phone: string;
	geom: {
		x: number;
		y: number;
	};
	geomDesc?: string;
}

export function createMerchant<T extends CreateMerchant>(merchant: T) {
	return lbManagement.post("/merchant", merchant);
}

export function getOwnMerchants() {
	return lbManagement.get<Merchant[]>("/merchant/own");
}

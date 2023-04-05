import { lbManagement } from '../request'

export function fetchCarByUid(uid: number){
	return lbManagement.get(`/car/${uid}`)
}

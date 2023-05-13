import useSWR from 'swr'
import { get } from './index'

export function fetchMerchant() {
  const { data, error, isLoading } = useSWR(
    'http://localhost:8080/lb/management/merchant/list',
    get
  )
  return {
    merchants: data,
    isLoading,
    isError: error,
  }
}

<template>
	<n-card class="rounded-16px shadow-sm">
		<n-layout>
			<n-layout>
				<n-layout-content>
					<n-data-table
						:columns="columns"
						:data="orders"
						:pagination="pagination"
						:row-key="(row) => row.oid"
						default-expand-all
					>
					</n-data-table>
				</n-layout-content>
			</n-layout>
		</n-layout>
	</n-card>
</template>

<script setup lang="tsx">
import { ref, reactive, Suspense, defineAsyncComponent } from 'vue'
import type { Ref } from 'vue'
import { NCard, NLayout, NLayoutContent, NDataTable, NButton } from 'naive-ui'
import type { DataTableColumns, PaginationProps } from 'naive-ui'
import {
	fetchOrders,
	fetchOrderDetail,
	acceptOrder,
	refuseOrder,
} from '@/service/api/merchant'
import { EnumOrderStatus } from '~/src/enum'
import { localStg } from '~/src/utils'

const Items = defineAsyncComponent(() => import('./item.vue'))

const orders: Ref<LB.Order[]> = ref([])

const columns: Ref<DataTableColumns<LB.Order>> = ref([
	{
		type: 'expand',
		title: '展开',
		expandable: () => true,
		renderExpand: (row) => {
			return (
				<Suspense>
					<Items oid={row.oid} />
				</Suspense>
			)
		},
	},
	{
		key: 'oid',
		title: 'oid',
		align: 'center',
	},
	{
		key: 'user.userName',
		title: '客户名称',
		align: 'center',
	},
	{
		key: 'status',
		title: '状态',
		align: 'center',
		render: (row) => {
			if (row.status == EnumOrderStatus.NONPAYMENT)
				return <n-tag type='default'>未支付</n-tag>
			else if (row.status == EnumOrderStatus.PENDING)
				return <n-tag type='primary'>已支付</n-tag>
			else if (row.status == EnumOrderStatus.CANCELLED)
				return <n-tag type='error'>用户取消</n-tag>
			else if (row.status == EnumOrderStatus.REFUSED)
				return <n-tag type='error'>商家拒绝</n-tag>
			else if (row.status == EnumOrderStatus.SHIPPED)
				return <n-tag type='info'>配送中</n-tag>
			else if (row.status == EnumOrderStatus.WAITING)
				return <n-tag type='info'>配送中</n-tag>
			else if (row.status == EnumOrderStatus.DELIVERED)
				return <n-tag tyep='success'>完成</n-tag>
		},
	},
	{
		key: 'totalAmount',
		title: '总金额',
		align: 'center',
	},
	{
		key: 'operation',
		title: '操作',
		render: (row) => {
			return (
				<>
					<NButton>查看</NButton>
					{row.status == EnumOrderStatus.PENDING ? (
						<>
							<NButton onClick={() => handleAcceptOrder(row.oid)}>接受</NButton>
							<NButton onClick={() => handleRefuseOrder(row.oid)}>拒绝</NButton>
						</>
					) : (
						<p></p>
					)}
				</>
			)
		},
	},
]) as Ref<DataTableColumns<LB.Order>>

const pagination: PaginationProps = reactive({
	page: 1,
	pageSize: 10,
	showSizePicker: true,
	pageSizes: [10, 15, 20, 25, 30],
	onChange: (page: number) => {
		pagination.page = page
	},
	onUpdatePageSize: (pageSize: number) => {
		pagination.pageSize = pageSize
		pagination.page = 1
	},
})

async function updateOrders() {
	const mid = getMerchantId()
	if (mid) {
		let { data } = await fetchOrders(mid)
		orders.value = data ?? []
	}
}

function getMerchantId() {
	const merchant = localStg.get('merchants')
	if (merchant && merchant[0]) return merchant[0].mid
	return null
}

async function init() {
	await updateOrders()
}

async function getOrderDetail(oid: number) {
	let { data } = await fetchOrderDetail(oid)
	return data
}

async function handleAcceptOrder(oid: number) {
	await acceptOrder(oid)
	updateOrders()
}

async function handleRefuseOrder(oid: number) {
	await refuseOrder(oid)
	updateOrders()
}

init()
</script>

<style lang="scss" scoped></style>

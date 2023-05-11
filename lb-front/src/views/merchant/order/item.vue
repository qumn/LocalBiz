<template>
	<n-card class="rounded-16px shadow-sm">
		<n-layout>
			<n-layout>
				<n-layout-content>
					<n-data-table
						:columns="itemColumns"
						:data="items"
						:row-key="(row) => row.oiid"
						default-expand-all
					>
					</n-data-table>
				</n-layout-content>
			</n-layout>
		</n-layout>
	</n-card>
</template>
<script setup lang="tsx">
import { ref, Ref } from 'vue'
import { NCard, NLayout, NLayoutContent, NDataTable } from 'naive-ui'
import type { DataTableColumns } from 'naive-ui'
import { fetchOrderDetail } from '@/service/api/merchant'

const props = defineProps<{
	oid: number
}>()

const items = (await fetchOrderDetail(props.oid))?.data?.items ?? [];

const itemColumns: Ref<DataTableColumns<LB.OrderItem>> = ref([
	{
		key: 'oiid',
		title: '订单项id',
		align: 'center',
	},
	{
		key: 'commodity.name',
		title: '商品名称',
		align: 'center',
	},
	{
		key: 'count',
		title: '数量',
		align: 'center',
	},
]) as Ref<DataTableColumns<LB.OrderItem>>
</script>

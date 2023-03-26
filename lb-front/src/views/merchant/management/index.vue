<template>
	<n-card class="rounded-16px shadow-sm">
		<n-layout has-sider>
			<n-layout-sider class="w-lg">
				<n-list>
					<n-list-item v-for="category in categories" :key="category.catId">
						<n-button
							@click="
								() => {
									updateCommodites(category.catId)
								}
							"
						>
							{{ category.name }}
						</n-button>
					</n-list-item>
				</n-list>
			</n-layout-sider>
			<n-layout>
				<n-layout-content>
					<n-space class="pb-12px ml-4" justify="space-between">
						<n-space>
							<n-button type="primary" @click="handleAddCommodity">
								<icon-ic-round-plus class="mr-4px text-20px" />
								新增
							</n-button>
							<n-button type="error">
								<icon-ic-round-delete class="mr-4px text-20px" />
								删除
							</n-button>
						</n-space>
					</n-space>
					<n-data-table
						:columns="columns"
						:data="commodities"
						:pagination="pagination"
					>
					</n-data-table>
				</n-layout-content>
			</n-layout>
		</n-layout>
		<table-action-modal
			v-model:visible="visible"
			:type="modalType"
			:edit-data="editData"
		/>
	</n-card>
</template>

<script setup lang="tsx">
import { ref, reactive } from 'vue'
import type { Ref } from 'vue'
import {
	NCard,
	NList,
	NListItem,
	NLayout,
	NLayoutSider,
	NLayoutContent,
	NImage,
	NDataTable,
	NButton,
	NSpace,
	NPopconfirm,
} from 'naive-ui'
import type { DataTableColumns, PaginationProps } from 'naive-ui'
import {
	fetchCategories,
	fetchCommodities,
	fetchCommodity,
} from '@/service/api/merchant'
import { localStg, ImageUrl } from '@/utils'
import TableActionModal, { ModalType } from './components/TableActionModal.vue'
import { useBoolean } from '@/hooks'

const { bool: visible, setTrue: openModal } = useBoolean()
const modalType = ref<ModalType>('add')
const editData = ref<LB.Commodity | null>(null)
let categories = ref<LB.Category[]>([])
let commodities = ref<LB.Commodity[]>([])

const columns: Ref<DataTableColumns<LB.Commodity>> = ref([
	{
		key: 'cid',
		title: 'id',
		align: 'center',
	},
	{
		key: 'name',
		title: '名称',
		align: 'center',
	},
	{
		key: 'img',
		title: '图片',
		align: 'center',
		render: (row) => {
			if (row.img) {
				return <NImage src={ImageUrl(row.img)} class='h20 wa'></NImage>
			}
			return <span>No Image</span>
		},
	},
	{
		key: 'desc',
		title: '详情',
		align: 'center',
	},
	{
		key: 'actions',
		title: '操作',
		align: 'center',
		render: (row) => {
			return (
				<NSpace justify={'center'}>
					<NButton size={'small'} onClick={() => handleEditCommodity(row.cid)}>
						编辑
					</NButton>
					<NPopconfirm onPositiveClick={() => handleDeleteCommodity(row.cid)}>
						{{
							default: () => '确认删除',
							trigger: () => <NButton size={'small'}>删除</NButton>,
						}}
					</NPopconfirm>
				</NSpace>
			)
		},
	},
]) as Ref<DataTableColumns<LB.Commodity>>

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

async function updateCategories() {
	const merchants = localStg.get('merchants') ?? []
	if (merchants.length > 0) {
		const merchant = merchants[0]
		const { data } = await fetchCategories(merchant.mid)
		categories.value = data ?? []
	} else {
		categories.value = []
	}
}

async function updateCommodites(catId: number) {
	let { data } = await fetchCommodities(catId)
	commodities.value = data ?? []
}

async function handleEditCommodity(cid: number) {
	let { data } = await fetchCommodity(cid)
	modalType.value = 'edit'
	editData.value = data
	openModal()
}
function handleDeleteCommodity(mid: number) {
	openModal()
}
function handleAddCommodity() {}
async function init() {
	await updateCategories()
	if (categories.value && categories.value.length > 0) {
		let catId = categories.value[0].catId
		await updateCommodites(catId)
	}
}

init()
</script>

<style lang="scss" scoped></style>

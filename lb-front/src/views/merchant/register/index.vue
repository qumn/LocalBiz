<script lang="ts" setup>
import { ref } from 'vue'
// import { NDataTable } from 'naive-ui'
import { getCategory } from '@/service/api/merchant'
import { localStg } from '@/utils'
let categories = ref<LB.Category[]>([])
//
async function getTableData() {
	const merchants = localStg.get('merchants') ?? []

	if (merchants.length > 0) {
		const merchant = merchants[0]
		const { data } = await getCategory(merchant.mid)
    categories.value = data ?? [];
    console.log(categories)
	}
}

async function init() {
	getTableData()
}

init()

</script>

<template>
	<h1>
		this is a page for registering merchant
	</h1>
</template>


<style scoped></style>

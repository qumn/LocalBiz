<script setup lang="ts">
import { localStg } from '~/src/utils/storage'
import { computed } from 'vue'
import { NSelect, SelectOption } from 'naive-ui'

const selectedMerchant = computed<LB.Merchant>({
	get: () => {
		return localStg.get('selectedMerchant')!
	},
	set: (val) => {
		localStg.set('selectedMerchant', val)
	},
})

function onChange(mid: number) {
	const merchant = merchants?.find((m) => m.mid === mid)
	if (merchant === undefined) throw new Error('merchant not found');
	selectedMerchant.value = merchant
}

const merchants = localStg.get('merchants')

const merchantOptions: Array<SelectOption> =
	merchants?.map((merchant) => {
		return {
			value: merchant.mid,
			label: merchant.name,
		}
	}) ?? []
</script>

<template>
	<div class="flex-center h-full">
		<n-select :default-value="selectedMerchant.mid" @update:value="onChange" :options="merchantOptions"> </n-select>
	</div>
</template>

<style scoped></style>

<template>
	<n-layout has-sider>
		<n-layout-sider :width="80">
			<div ref="specsDiv">
				<span>价格</span>
				<n-list>
					<n-list-item v-for="spec in specs" :key="spec.sid">
						<n-input-number
							:show-button="false"
							:class="{ active: selected && selected.sid == spec.sid }"
							@focus="updateSelected(spec)"
							v-model:value="spec.price"
						/>
					</n-list-item>
				</n-list>
				<n-button @click="addSpc()" class="w-100%">
					<icon-ic-round-plus class="mr-4px text-20px" />
				</n-button>
			</div>
		</n-layout-sider>
		<n-layout-content>
			<div v-if="selected" ref="atbDiv">
				<div v-for="atb in selected.attributes" :key="atb.aid">
					<n-grid :cols="2">
						<n-gi :x-gap="12">
							<n-input placeholder="value" v-model:value="atb.value" />
						</n-gi>
						<n-gi :x-gap="12">
							<n-input placeholder="key" v-model:value="atb.key" />
						</n-gi>
					</n-grid>
				</div>
				<n-grid :cols="1">
					<n-gi>
						<n-button @click="addAtb(selected)" class="w-100%">
							<icon-ic-round-plus class="mr-4px text-20px" />
						</n-button>
					</n-gi>
				</n-grid>
			</div>
			<n-empty v-else></n-empty>
		</n-layout-content>
	</n-layout>
</template>

<script setup lang="ts">
import { computed, onMounted, onUnmounted, ref } from 'vue'

export interface Props {
	value?: LB.Specification[]
}
const props = defineProps<Props>()
const specs = computed({
	get() {
		return props.value || []
	},
	set(specs: LB.Specification[]) {
		console.log('set specs new value: ' + JSON.stringify(specs))
		emits('update:value', specs)
	},
})

const atbDiv = ref<HTMLDivElement | null>(null)
const specsDiv = ref<HTMLDivElement | null>(null)
let selected = ref(specs.value.length > 0 ? specs.value[0] : null)

interface Emits {
	(e: 'update:value', value: LB.Specification[]): void
}
const emits = defineEmits<Emits>()

function addSpc() {
	const spc = {
		price: null,
		attributes: [],
	} as unknown as LB.Specification
	specs.value.push(spc)
	selected.value = spc
}

function addAtb(specs: LB.Specification | null) {
	if (specs) {
		specs.attributes.push({
			key: '',
			value: '',
		} as LB.SpecificationAbt)
	}
}
function updateSelected(spec: LB.Specification) {
	console.log('update selected')
	selected.value = spec
}

function filterEmptySpeAtb(spec: LB.Specification | null) {
	if (spec) {
		spec.attributes = spec.attributes.filter((atb) => atb.key || atb.value)
	}
}
function filterEmptySpecs() {
	if (specs) {
		const filted = specs.value.filter(
			(spec) => spec.price || spec.sid || spec.attributes.length > 0
		)
		if (!filted.find(t => t == selected.value)) {
			selected.value = props.value?.length ? props.value[0] : null
		}
		specs.value = filted
	}
}

const outsideClick = (event: MouseEvent) => {
	if (!atbDiv.value?.contains(event.target as Node)) {
		filterEmptySpeAtb(selected.value)
	}
	if (!specsDiv.value?.contains(event.target as Node)) {
		filterEmptySpecs()
	}
}

onMounted(() => {
	document.addEventListener('click', outsideClick)
})
onUnmounted(() => {
	document.removeEventListener('click', outsideClick)
})
</script>

<style lang="scss" scoped>
.active {
	color: blue;
}
</style>

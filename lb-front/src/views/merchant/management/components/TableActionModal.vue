<template>
	<n-modal
		v-model:show="modalVisible"
		preset="card"
		:title="title"
		class="w-700px"
	>
		<n-form
			ref="formRef"
			label-placement="left"
			:label-width="80"
			:modal="formModel"
		>
			<n-grid :cols="24" :y-gap="12">
				<n-form-item-grid-item :offset="3" :span="18" label="名称">
					<n-input v-model:value="formModel.name"></n-input>
				</n-form-item-grid-item>
				<n-form-item-grid-item :offset="3" label="图片">
					<upload v-model:img="formModel.img"></upload>
				</n-form-item-grid-item>
				<n-form-item-grid-item :offset="3" :span="18" label="简介">
					<n-input v-model:value="formModel.desc"></n-input>
				</n-form-item-grid-item>
				<n-form-item-grid-item :offset="3" :span="18" label="规格">
					<specitication
						v-model:value="formModel.specifications"
					></specitication>
				</n-form-item-grid-item>
			</n-grid>
			<n-space class="w-full pt-16px" :size="24" justify="end">
				<n-button class="w-72px" @click="closeModal">取消</n-button>
				<n-button class="w-72px" type="primary" @click="handleSubmit"
					>确定</n-button
				>
			</n-space>
		</n-form>
	</n-modal>
</template>

<script setup lang="ts">
import { ref, computed, reactive, watch } from 'vue'
import { NModal } from 'naive-ui'
import type { FormInst } from 'naive-ui'
import Upload from '@/components/common/Upload.vue'
import Specitication from './Specitication.vue'
import { putCommodity, postCommodity } from '@/service/api/merchant'

export interface Props {
	visible: boolean
	type?: 'add' | 'edit'
	editData?: LB.Commodity | null
}

export type ModalType = NonNullable<Props['type']>

defineOptions({ name: 'TableActionModal' })

const props = withDefaults(defineProps<Props>(), {
	type: 'add',
	editData: null,
})

interface Emits {
	(e: 'update:visible', visible: boolean): void
}

const emit = defineEmits<Emits>()

const modalVisible = computed({
	get() {
		return props.visible
	},
	set(visible) {
		emit('update:visible', visible)
	},
})

const closeModal = () => {
	modalVisible.value = false
}

const title = computed(() => {
	const titles: Record<ModalType, string> = {
		add: '添加商品',
		edit: '编辑商品',
	}
	return titles[props.type]
})

const formRef = ref<HTMLElement & FormInst>()
type FormModel = Pick<
	LB.Commodity,
	'mid' | 'img' | 'desc' | 'name' | 'specifications'
>
const formModel = reactive<FormModel>(createDefualtFormModel())

function createDefualtFormModel(): FormModel {
	return {
		mid: 0,
		img: '',
		desc: '',
		name: '',
		specifications: [],
	}
}

function handleUpdateFormModel(model: Partial<FormModel>) {
	Object.assign(formModel, model)
}

function handleUpdateFormModelByModalType() {
	const handles: Record<ModalType, () => void> = {
		add: () => {
			const defaultFormModel = createDefualtFormModel()
			handleUpdateFormModel(defaultFormModel)
		},
		edit: () => {
			if (props.editData) {
				handleUpdateFormModel(props.editData)
			}
		},
	}
	handles[props.type]()
}

function handleSubmit() {
	if (props.type == 'edit') {
		putCommodity(formModel as LB.Commodity)
	} else if (props.type == 'add') {
		postCommodity(formModel as LB.Commodity)
	}
	modalVisible.value = false
}

watch(
	() => props.visible,
	(newValue) => {
		if (newValue) {
			handleUpdateFormModelByModalType()
		}
	}
)
</script>

<style lang="scss" scoped></style>

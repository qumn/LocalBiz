<template>
	<n-upload
		:custom-request="upload"
		:default-file-list="fileList"
		list-type="image-card"
		:multiple="false"
		:max="1"
		class="hover-text-blue"
	>
		<icon-ic-baseline-plus class="text-grey text-40px" />
	</n-upload>
</template>

<script setup lang="ts">
import { UploadCustomRequestOptions, UploadFileInfo } from 'naive-ui'
import { uploadFile } from '@/service/api/file'
import { computed, reactive, watchEffect } from 'vue'
import { ImageUrl } from '@/utils'

export interface Props {
	img: string
}
const props = defineProps<Props>()

interface Emits {
	(e: 'update:img', img: string): void
}
const emit = defineEmits<Emits>()

const imgUrl = computed({
	get() {
		return props.img
	},
	set(url: string) {
		emit('update:img', url)
	},
})

const fileList = reactive<UploadFileInfo[]>([])

async function upload({ file, onProgress }: UploadCustomRequestOptions) {
	let rsp: any = await uploadFile(file.file as File, (e: any) => {
		onProgress?.({ percent: Math.floor((e.loaded / e.total) * 100) })
	})
	fileList.splice(0)
	fileList.push({
		id: rsp.data.id,
		name: rsp.data.name,
		url: ImageUrl(rsp.data.url),
		status: 'finished', // TODO: set this by rsp
	})
	imgUrl.value = rsp.data.url
}

function echo(url: string) {
	fileList.splice(0)
	console.log('image url: ' + ImageUrl(url))
	fileList.push({
		id: '0',
		name: '',
		url: ImageUrl(url),
		status: 'finished',
	})
}

watchEffect(() => {
	echo(props.img)
})
</script>

<style lang="scss" scoped></style>

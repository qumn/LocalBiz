<template>
	<n-form ref="formRef" :model="model" :rules="rules" size="large" :show-label="false">
		<n-form-item path="name">
			<n-input v-model:value="model.name" placeholder="商铺名称" />
		</n-form-item>
		<n-form-item path="phone">
			<n-input v-model:value="model.phone" placeholder="手机号码" />
		</n-form-item>
		<n-form-item>
			<n-input v-model:value="model.desc" placeholder="商店介绍"></n-input>
		</n-form-item>
		<n-form-item>
			<n-upload :custom-request="upload" :default-file-list="fileList" list-type="image-card" :multiple="false" :max="1">
				<n-button type="primary" size="small">商铺图片</n-button>
			</n-upload>
		</n-form-item>
		<n-form-item>
			<n-input v-model:value="model.geomDesc" placeholder="商店地址"></n-input>
		</n-form-item>
		<n-form-item>
			<gaode-map :modelValue="location" @update:modelValue="updateLocation" style="height: 30vh"></gaode-map>
		</n-form-item>
		<n-space :vertical="true" :size="18">
			<login-agreement v-model:value="agreement" />
			<n-button type="primary" size="large" :block="true" :round="true" @click="handleSubmit">确定</n-button>
			<n-button size="large" :block="true" :round="true" @click="toLoginModule('pwd-login')">返回</n-button>
		</n-space>
	</n-form>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue';
import { FormInst, FormRules, UploadCustomRequestOptions, UploadFileInfo } from 'naive-ui';
import { useRouterPush } from '@/composables';
import { formRules  } from '@/utils';
import { uploadFile } from '@/service/api/file'
import { createMerchant } from '~/src/service/api/merchant';
import { GaodeMap } from '~/src/views/plugin/map/components';


const { toLoginModule } = useRouterPush();

const formRef = ref<HTMLElement & FormInst>();
const fileList = ref<UploadFileInfo[]>([]);
const location = reactive({
	longitude: 116.397428,
	latitude: 39.909195,
	address: '',
	zone: []
});

function updateLocation(val: any) {
	model.geom = {x: val.longitude, y: val.latitude};
	model.geomDesc = val.address
}

// upload file
async function upload({ file, data, onProgress }: UploadCustomRequestOptions) {
	let rsp: any = await uploadFile(file.file as File, (e: any) => {
		onProgress?.({ percent: Math.floor(e.loaded / e.total * 100) });
	});
	fileList.value = []
	fileList.value.push({
		id: rsp.data.id,
		name: rsp.data.name,
		url: rsp.data.url,
		status: "finished"
	})
	model.introImg = rsp.data.url;
}

const model = reactive({
	name: '',
	phone: '',
	desc: '',
	geom: {x: 0, y: 0},
	geomDesc: '',
	introImg: '',
});

const rules: FormRules = {
	phone: formRules.phone,
	code: formRules.code,
};

const agreement = ref(false);

async function handleSubmit() {
	await formRef.value?.validate();
	window.$message?.success('验证成功!');
	await createMerchant(model);
	const {toHome} = useRouterPush(false);
	toHome();

}
</script>

<style scoped></style>

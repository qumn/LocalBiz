<template>
	<n-form ref="formRef" :model="model" size="large" :show-label="false">
		<n-form-item path="userName">
			<n-input v-model:value="model.username" placeholder="请输入用户名" />
		</n-form-item>
		<n-form-item path="password">
			<n-input v-model:value="model.password" type="password" show-password-on="click" placeholder="请输入密码" />
		</n-form-item>
		<n-form-item path="imgCode">
			<n-input v-model:value="model.code" placeholder="验证码,点击图片刷新" />
			<div class="pl-8px">
				<!-- <image-verify v-model:code="imgCode" @click="getCode"/> -->
				<img :src="CodeImg" @click="getCode" />
			</div>
		</n-form-item>
		<n-space :vertical="true" :size="24">
			<div class="flex-y-center justify-between">
				<n-checkbox v-model:checked="rememberMe">记住我</n-checkbox>
				<n-button :text="true" @click="toLoginModule('reset-pwd')">忘记密码？</n-button>
			</div>
			<n-button type="primary" size="large" :block="true" :round="true" :loading="auth.loginLoading"
				@click="handleSubmit">
				确定
			</n-button>
			<div class="flex-y-center justify-between">
				<n-button class="flex-1" :block="true" @click="toLoginModule('code-login')">
					{{ EnumLoginModule['code-login'] }}
				</n-button>
				<div class="w-12px"></div>
				<n-button class="flex-1" :block="true" @click="toLoginModule('register')">
					{{ EnumLoginModule.register }}
				</n-button>
			</div>
		</n-space>
	</n-form>
</template>

<script setup lang="ts">
import { reactive, ref, computed, onMounted } from 'vue';
import type { FormInst } from 'naive-ui';
import { EnumLoginModule } from '@/enum';
import { useAuthStore } from '@/store';
import { useRouterPush } from '@/composables';
import { fetchCode } from '@/service/api/auth'

const auth = useAuthStore();
const { login } = useAuthStore();
const { toLoginModule } = useRouterPush();

const formRef = ref<HTMLElement & FormInst>();
const imgCode = ref('');


const model = reactive({
	username: '',
	password: '',
	code: '',
	uuid: '',
});

const rememberMe = ref(false);

const CodeImg = computed(() => {
	return 'data:image/png;base64,' + imgCode.value;
});


async function getCode() {
	const { data }: { data: any } = await fetchCode();
	console.log(data);
	imgCode.value = data?.img;
	model.uuid = data?.uuid;
}


async function handleSubmit() {
	await formRef.value?.validate();
	login(model);
}


onMounted(async () => {
	await getCode();
})

</script>

<style scoped></style>

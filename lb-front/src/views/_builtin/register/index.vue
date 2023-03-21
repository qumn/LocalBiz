<script setup lang="ts">
import { computed } from 'vue';
import { Register } from '../login/components'
import { useThemeStore } from '@/store';
import { mixColor } from '@/utils';

const theme = useThemeStore();

const bgColor = computed(() => {
  const COLOR_WHITE = '#ffffff';
  const ratio = theme.darkMode ? 0.5 : 0.2;
  return mixColor(COLOR_WHITE, theme.themeColor, ratio);
});
</script>
<template>
	<div class="relative flex-center wh-full" :style="{ backgroundColor: bgColor }">
		<dark-mode-switch :dark="theme.darkMode" class="absolute left-48px top-24px z-3 text-20px"
			@update:dark="theme.setDarkMode" />
		<n-card :bordered="false" size="large" class="z-4 !w-auto rounded-20px shadow-sm">
			<register></register>
		</n-card>
	</div>
</template>

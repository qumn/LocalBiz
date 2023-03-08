<template>
	<div class="map-wrapper h-full w-full">
		<div id="mapcontainer"></div>
		<div class="search-box">
			<n-auto-complete v-model:value="keyword" :input-props="{
				autocomplete: 'disabled'
			}" :options="options" @select="handleSelect" placeholder="输入城市+关键字搜索" style="width: 300px" />
			<!-- <el-autocomplete v-model="keyword" :fetch-suggestions="handleSearch" :trigger-on-focus="false" clearable
				placeholder="输入城市+关键字搜索" @select="handleSelect" style="width: 300px" /> -->
			<!-- <n-input v-model:value="location.longitude" placeholder="点击地图选择经度" maxlength="15" readonly
				style="width: 150px; margin: 0 5px"></n-input>
			<n-input v-model:value="location.latitude" placeholder="点击地图选择纬度" maxlength="15" readonly
				style="width: 150px"></n-input> -->
		</div>
	</div>
</template>

<script setup>
import { shallowRef, computed, watch, ref, onMounted } from 'vue';
import AMapLoader from '@amap/amap-jsapi-loader';

const keyAndSecJsCode = {
	securityJsCode: '0c05801113ca9b7af2098e901c1a3516',
	key: 'a50ef296f226a552dd136002f2cf254e',
	version: '2.0'
}

window._AMapSecurityConfig = {
	securityJsCode: keyAndSecJsCode.securityJsCode
};

const props = defineProps({
	modelValue: {
		type: Object,
		default() {
			return {};
		},
	},
});
const emit = defineEmits(['update:modelValue']);

const map = shallowRef(null);
// 地点
const location = ref({});
const keyword = ref('');
const options = ref([]);

let placeSearch, AMapObj, marker, geocoder;

watch(location, (val) => {
	if (val.longitude && val.latitude) {
		console.log("location", val);
		emit('update:modelValue', val);
		drawMarker();
	}
});
watch(keyword, async (val) => {
	if (!val) return [];
	if (!placeSearch) return [];
	const list = await searchPlace(val);
	list.forEach(item => {
		item.value = item;
		item.label = item.name;
	});
	options.value = list;
});

onMounted(() => {
	initMap();
});

function initMap() {
	AMapLoader.load({
		key: keyAndSecJsCode.key,
		version: keyAndSecJsCode.version
	}).then(AMap => {
		AMapObj = AMap;
		map.value = new AMap.Map('mapcontainer');
		// 添加点击事件
		map.value.on('click', onMapClick);
		if (location.value.longitude) {
			drawMarker();
		}
		AMap.plugin(
			['AMap.ToolBar', 'AMap.Scale', 'AMap.Geolocation', 'AMap.PlaceSearch', 'AMap.Geocoder'],
			() => {
				// 缩放条
				const toolbar = new AMap.ToolBar();
				// 比例尺
				const scale = new AMap.Scale();
				// 定位
				const geolocation = new AMap.Geolocation({
					enableHighAccuracy: true, //是否使用高精度定位，默认:true
					timeout: 10000, //超过10秒后停止定位，默认：5s
					position: 'RT', //定位按钮的停靠位置
					buttonOffset: new AMap.Pixel(10, 20), //定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
					zoomToAccuracy: true, //定位成功后是否自动调整地图视野到定位点
				});
				geocoder = new AMap.Geocoder({
					city: '全国',
				});
				map.value.addControl(geolocation);
				map.value.addControl(toolbar);
				map.value.addControl(scale);
				placeSearch = new AMap.PlaceSearch({
					map: map.value,
					city: '',
					pageSize: 30, // 单页显示结果条数
					pageIndex: 1, // 页码
					citylimit: true, // 是否强制限制在设置的城市内搜索
					autoFitView: true,
				});
			}
		);
	})
}

async function searchPlace(keyword) {
	return new Promise((resolve, reject) => {
		placeSearch.search(keyword, (status, result) => {
			if (result && typeof result === 'object' && result.poiList) {
				const list = result.poiList.pois;
				resolve(list);
			} else { resolve([]) }
		});
	})
}

// 点击地图
function onMapClick(e) {
	console.log("onMapClick", e);
	const { lng, lat } = e.lnglat;
	// 逆地理编码
	geocoder.getAddress([lng, lat], (status, result) => {
		console.log("geocoder", status, result);
		if (status === 'complete' && result.info === 'OK') {
			const { addressComponent, formattedAddress } = result.regeocode;
			let { city, province, district } = addressComponent;
			if (!city) {
				// 直辖市
				city = province;
			}
			location.value = {
				longitude: lng,
				latitude: lat,
				address: formattedAddress,
				zone: [province, city, district],
			};
		}
	});
}

// 点击搜索项
function handleSelect(item) {
	console.log("handleSelect", item);
	const { pname, cityname, adname, address, name } = item;
	const { lng, lat } = item.location;
	location.value = {
		longitude: lng,
		latitude: lat,
		address,
		zone: [pname, cityname, adname],
		name,
	};
	map.value.setZoomAndCenter(16, [lng, lat]);
}

// 绘制地点marker
function drawMarker(val) {
	const { longitude, latitude } = location.value || val;
	if (marker) {
		marker.setMap(null);
	}
	marker = new AMapObj.Marker({
		position: new AMapObj.LngLat(longitude, latitude),
		anchor: 'bottom-center',
	});
	map.value.add(marker);
	map.value.setZoomAndCenter(16, [longitude, latitude]);
}
</script>

<style scoped>
.map-wrapper {
	position: relative;

}

#mapcontainer {
	width: 100%;
	height: 100%;
}

.map-wrapper .search-box {
	position: absolute;
	top: 10px;
	left: 10px;
	z-index: 1;
	display: flex;
	align-items: center;
}
</style>

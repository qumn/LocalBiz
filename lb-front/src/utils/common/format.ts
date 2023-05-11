// 转换人民币单位, 分转元
// 1. 保留两位小数
export function formatMoney(money: number) {
	return (money / 100).toFixed(2);
}

import { fileRequest } from "../request";

// upload fild
export const uploadFile = (
	file: File,
	onUploadProgress?: (progressEvent: any) => void
) => {
	const formData = new FormData();
	formData.append("file", file);
	return fileRequest.post("/upload", formData, { onUploadProgress });
};

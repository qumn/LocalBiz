import { getServiceEnvConfig } from "~/.env-config";
import { createRequest } from "./request";

const { url, urlPattern, secondUrl, secondUrlPattern } = getServiceEnvConfig(
	import.meta.env
);

const isHttpProxy = import.meta.env.VITE_HTTP_PROXY === "Y";

export const request = createRequest({
	baseURL: isHttpProxy ? urlPattern : url,
});

export const secondRequest = createRequest({
	baseURL: isHttpProxy ? secondUrlPattern : secondUrl,
});

export const mockRequest = createRequest({ baseURL: "/mock" });

export const fileRequest = createRequest({ baseURL: "/api/file" });

export const authRequest = createRequest({ baseURL: "/api/auth" });

export const lbManagement = createRequest({ baseURL: "/api/lb/management" });

export const systemRequest = createRequest(
	{ baseURL: "/api/system" },
	{ dataKey: "*" }
);

export const apiRequest = createRequest(
	{
		baseURL: "/api",
	},
	{
		dataKey: "*",
	}
);

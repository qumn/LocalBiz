
const { VITE_FILE_URL } = import.meta.env;

export function ImageUrl(name: string){
	if(name.startsWith('http')){
		return name;
	}
  return `${VITE_FILE_URL}/${name}`;
}

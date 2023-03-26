
const { VITE_FILE_URL } = import.meta.env;
export function ImageUrl(name: string){
  return `${VITE_FILE_URL}/${name}`;
}

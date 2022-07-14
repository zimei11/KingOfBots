import { useDark, useToggle } from '@vueuse/core'

export const isDark = useDark()
export const toggleDark = useToggle(isDark)

export const chooseDark = ()=>{
    // console.log(isDark.value=false);
    // console.log(toggleDark())
    isDark.value=false;
    toggleDark();
}
export const chooseLight = ()=>{
    isDark.value=true;
    toggleDark();
}
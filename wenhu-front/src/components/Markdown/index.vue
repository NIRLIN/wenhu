<template>
  <div id="markdown">
    <mavon-editor ref="md" :toolbars="toolbars" :box-shadow-style="'0 2px 12px 0 rgba(1, 0, 0, 0.1)'" :box-shadow="false" :default-open="'edit'" @imgAdd="$imgAdd" @change="change" />
  </div>

</template>

<script>
import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import { imageUpload } from '@/api/util'
import { getCookie } from '@/utils/login-status.js'
import TurndownService from 'turndown'

export default {
  name: 'Markdown',
  components: {
    mavonEditor
  },
  props: {
    value: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      content: '',
      html: '',
      toolbars: {
        bold: true, // 粗体
        italic: true, // 斜体
        header: true, // 标题
        underline: true, // 下划线
        strikethrough: true, // 中划线
        mark: true, // 标记
        superscript: false, // 上角标
        subscript: false, // 下角标
        quote: true, // 引用
        ol: true, // 有序列表
        ul: true, // 无序列表
        link: true, // 链接
        imagelink: true, // 图片链接
        code: true, // code
        table: true, // 表格
        fullscreen: true, // 全屏编辑
        readmodel: true, // 沉浸式阅读
        htmlcode: false, // 展示html源码
        help: true, // 帮助
        /* 1.3.5 */
        undo: true, // 上一步
        redo: true, // 下一步
        trash: true, // 清空
        save: false, // 保存（触发events中的save事件）
        /* 1.4.2 */
        navigation: true, // 导航目录
        /* 2.1.8 */
        alignleft: true, // 左对齐
        aligncenter: true, // 居中
        alignright: true, // 右对齐
        /* 2.2.1 */
        subfield: false, // 单双栏模式
        preview: true // 预览
      }
    }
  },
  mounted() {
    const turndownService = new TurndownService()
    this.$refs.md.d_value = turndownService.turndown(this.value)
  },
  methods: {
    change(value, html) {
      this.$emit('listenToChildEvent', html)
    },
    // 绑定@imgAdd event
    $imgAdd(pos, $file) {
      // 第一步.将图片上传到服务器.
      const formData = new FormData()
      formData.append('image', $file)
      formData.append('id', getCookie())
      imageUpload(formData).then((response) => {
        // console.log(response.data)
        this.$refs.md.$img2Url(pos, response.data)
      })
    }
  }
}
</script>

<style scoped>
#markdown{
  border:1px solid  #E4E7ED
}
</style>

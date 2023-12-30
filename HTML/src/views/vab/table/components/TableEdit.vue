<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogFormVisible"
    width="500px"
    @close="close"
  >
    <el-form ref="form" label-width="80px" :model="form" :rules="rules">
      <el-form-item label="Food or not" prop="photo_type">
        <el-input v-model.trim="form.photo_type" autocomplete="off" />
      </el-form-item>
      <el-form-item label="Type of food" prop="photo_kind">
        <el-input v-model.trim="form.photo_kind" autocomplete="off" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="close">Cancel</el-button>
      <el-button type="primary" @click="save">Save</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import { EditPhoto } from '@/api/photoPage'


  export default {
    name: 'TableEdit',

    data() {
      return {
        form: {
          photo_type: '',
          photo_kind: '',
        },
        rules: {
          photo_type: [{ required: true, trigger: 'blur', message: '请输入标题' }],
          photo_kind: [{ required: true, trigger: 'blur', message: '请输入作者' }],
        },
        title: '',
        dialogFormVisible: false,
      }
    },
    created() {},
    methods: {
      showEdit(row) {
        if (!row) {
          this.title = '添加'
        } else {
          this.title = '编辑'
          this.form = Object.assign({}, row)
        }
        this.dialogFormVisible = true

      },
      close() {
        this.$refs['form'].resetFields()
        this.form = this.$options.data().form
        this.dialogFormVisible = false
        this.$emit('fetch-data')
      },
      save() {
        this.$refs['form'].validate(async (valid) => {
          if (valid) {
            const { msg } = await EditPhoto(this.form)
            this.$baseMessage(msg, 'success')
            this.$refs['form'].resetFields()
            this.dialogFormVisible = false
            this.$emit('fetch-data')
            this.form = this.$options.data().form
          } else {
            return false
          }
        })
      },
    },
  }
</script>

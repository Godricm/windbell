<template>
  <div>
    <Card>
      <div class="search-con search-con-top">
        <Form :model="form" :label-width="80" inline>
          <FormItem label="名称">
            <Input v-model="form.name" placeholder="悟耘"></Input>
          </FormItem>
          <FormItem label="类型">
            <Select v-model="form.merType">
              <Option value="" selected="selected">全部</Option>
              <Option :value="item.key" v-for="(item, index) in merTypeOptions" :key="index">{{item.name}}</Option>
            </Select>
          </FormItem>
          <FormItem label="联系人">
            <Input v-model="form.linkman" placeholder="小明"></Input>
          </FormItem>
          <FormItem label="电话">
            <Input v-model="form.phone" placeholder="131 8888 8888"></Input>
          </FormItem>
          <FormItem label="状态">
            <Select v-model="form.status">
              <Option value="" selected="selected">全部</Option>
              <Option :value="item.key" v-for="(item, index) in statusOptions" :key="index">{{item.name}}</Option>
            </Select>
          </FormItem>
        </Form>
        <div class="toolbar">
          <Button type="primary" icon="md-add" @click="handleCreate">新增</Button>
          <Button type="primary" icon="md-trash" @click="handleDelete">删除</Button>
          <Button type="primary" icon="md-trash" @click="handleInit">初始化</Button>
          <Button @click="handleSearch" icon="md-search" class="search-btn" type="primary">搜索</Button>
        </div>
      </div>
      <tables ref="tables" v-model="tableData" :columns="columns" @on-search="handleSearch"
              @on-update="handleUpdate" @on-detail="handleDetail" @on-selection-change="selectionChange"/>
    </Card>
    <tenantInfo ref="tenantInfoRef" @handle-search="handleSearch"></tenantInfo>
    <Modal
      v-model="sureInitModel"
      title="是否初始化"
      @on-ok="handleInitSure">
      <p>同步当前所有部门,角色,用户到对应平台</p>
    </Modal>
  </div>
</template>

<script>
import Tables from '_c/tables'
import tenantInfo from './tenant-info'
import { getIds } from '@/libs/util'

export default {
  name: 'tables_page',
  components: {
    Tables,
    tenantInfo
  },
  data () {
    return {
      columns: [
        { title: '', key: 'id', type: 'selection', width: 60 },
        { title: '名称', key: 'name' },
        { title: '类型',
          key: 'merType',
          render: (h, params) => {
            return h('span', this.getDictVal('tenant_type', params.row.merType))
          } },

        { title: '营业执照', key: 'creditCode' },
        { title: '联系人', key: 'linkman' },
        { title: '联系电话', key: 'phone' },
        { title: '短信签名',
          key: 'smsSign',
          render: (h, params) => {
            return h('span', '【' + params.row.smsSign + '】')
          }},
        { title: '平台',
          key: 'pass',
          render: (h, params) => {
            return h('span', this.getDictVal('paas_type', params.row.paas))
          } },
        { title: '状态',
          key: 'status',
          render: (h, params) => {
            return h('span', this.getDictVal('tenant_status', params.row.status))
          } },
        { title: '创建时间', key: 'crtTime', sortType: 'desc' },
        {
          title: '操作',
          key: 'handle',
          minWidth: 200,
          options: ['update', 'detail']
        }
      ],
      tableData: {
        rows: [],
        total: 0
      },
      form: {
        map: {}
      },
      selectedData: [],
      merTypeOptions: [],
      statusOptions: [],
      sureInitModel: false
    }
  },
  methods: {
    handleUpdate (param) {
      this.$refs.tenantInfoRef.openModel('update', param.row)
    },
    handleDetail (param) {
      this.$refs.tenantInfoRef.openModel('detail', param.row)
    },
    handleCreate () {
      this.$refs.tenantInfoRef.openModel('create')
    },
    handleInit () {
      this.sureInitModel = true
    },
    handleInitSure () {
      let ids = getIds(this.selectedData)
      if (ids.length !== 1) {
        this.$Message.error('只允许选一个客户')
        return
      }
      this.P('admin/tenant/paasInit/' + ids[0]).then(data => {
        this.$Message.success(data)
        this.handleSearch()
      })
    },
    handleDelete () {
      this.D('admin/tenant', getIds(this.selectedData)).then(data => {
        this.$Message.success(data)
        this.handleSearch()
      })
    },
    selectionChange (selection) {
      this.selectedData = selection
    },
    exportExcel () {
      this.$refs.tables.exportCsv({
        filename: `table-${(new Date()).valueOf()}.csv`
      })
    },
    handleSearch (page, pageSize) {
      if (isNaN(page)) {
        page = 1
      }
      var param = {
        page: page,
        pageSize: pageSize,
        model: this.form,
        map: this.form.map
      }
      this.L('admin/tenant', param).then(data => {
        this.tableData = data
      })
    }
  },
  mounted () {
    this.handleSearch()
    this.dict('tenant_type').then(data => {
      this.merTypeOptions = data
    })
    this.dict('tenant_status').then(data => {
      this.statusOptions = data
    })
  }
}
</script>

<style>

</style>

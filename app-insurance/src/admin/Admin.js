const insured = {
  insuredBirthday: '',
  insuredCareer: '',
  insuredName: '',
  insuredCardType: [],
  insuredCardNo: '',
  insuredCardPeriod: '',
  insuredCountry: '',
  insuredHeight: '',
  insuredBodyWeight: '',
  insuredIncome: '',
  insuredMarriage: [],
  insuredTel:'',
  insuredMobile:'',
  insuredEmail:'',
  insuredProvince:'',
  insuredCity:'',
  insuredDistrict:'',
  insuredAddress:'',
  insuredRelation:[],
};

const holder = {
  policyholderName: '',
  policyholderGender: true,
  policyholderBirthday:'',
  policyholderCareer:'',
  policyholderCardType:[],
  policyholderCardNo:'',
  policyholderCountry:'',
  policyholderCardPeroid:'',
  policyholderHeight:'',
  policyholderBodyWeight:'',
  policyholderIncome:'',
  policyholderMarriage:[],
  policyholderTel:'',
  policyholderMobile:'',
  policyholderEmail:'',
  policyholderProvince:'',
  policyholderCity:'',
  policyholderDistrict:'',
  policyholderAddress:'',
  policyholderTaxRelated:[],
  policyholderSign:'',
  cardPositiveImg:'',
  cardNegativeImg:'',
  documentNum:'',
  marital:[]
};

const order = {
  orderCode:'',
  insuranceProduct:{prodId:''},
  insuranceProductPrice:{priceId:''},
  insuranceOrderPolicyholderBank:{},
  LegalBeneficiary:'',
  insuranceOrderBeneficiarys:[],
};

const bank = {
  bankName:'',
  amount:'',
  bankMobile:'',
  accountBank:'',
  bankProvince:'',
  bankCity:'',
  accountType:'',
  accountNo:'',
  bankCardImg:''
};

export default
{
  insured,
  holder,
  order,
  bank
}



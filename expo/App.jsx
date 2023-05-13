import { StatusBar } from 'expo-status-bar'
import { StyleSheet, Text, View, TextInput, Image } from 'react-native'
import { Button } from './components/button'
import colors from './colors'
import { fetchMerchant } from './apis/merchant'

function Search() {
  return <TextInput style={styles.searchIput}></TextInput>
}

function Content() {
  return (
    <View>
      <Text>content</Text>
    </View>
  )
}

function Bottom() {
  return (
    <View style={styles.bottom}>
      <Button icon={'home'} text={'首页'}></Button>
      <Button icon={'shopping-cart'} text={'购物车'}></Button>
      <Button icon={'inbox'} text={'个人信息'}></Button>
    </View>
  )
}
function Merchats() {
  const { merchants, isLoading, isError } = fetchMerchant()
  if (isLoading) {
    return <Text>loading...</Text>
  }
  if (isError) {
    return <Text>error</Text>
  }
  return <Text>{ JSON.stringify(merchants) }</Text>
}

export default function App() {
  const blurhash =
    '|rF?hV%2WCj[ayj[a|j[az_NaeWBj@ayfRayfQfQM{M|azj[azf6fQfQfQIpWXofj[ayj[j[fQayWCoeoeaya}j[ayfQa{oLj?j[WVj[ayayj[fQoff7azayj[ayj[j[ayofayayayj[fQj[ayayj[ayfjj[j[ayjuayj['

  return (
    <View style={styles.container}>
      <View style={styles.searchContainer}>
        <View style={styles.searchLeft}>
          <Image
            style={styles.image}
            source='https://picsum.photos/seed/696/3000/2000'
            placeholder={blurhash}
            contentFit='cover'
            transition={1000}
          />
        </View>
        <View style={styles.searchRight}>
          <Search />
        </View>
      </View>
      <Merchats></Merchats>
      <View style={styles.contentContainer}>
        <Content style={styles.content} />
      </View>
      <View style={styles.bottomContainer}>
        <Bottom />
      </View>
      <StatusBar style='auto' />
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingTop: 20,
    alignItems: 'center',
    justifyContent: 'space-between',
    backgroundColor: colors.white,
  },
  searchContainer: {
    flex: 1,
    width: '100%',
    paddingTop: 20,
    flexDirection: 'row',
  },
  searchLeft: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  searchRight: {
    flex: 4,
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
  },
  image: {
    width: 50,
    height: 50,
    borderRadius: 50,
    backgroundColor: '#0553',
  },
  searchIput: {
    height: 40,
    borderRadius: 20,
    width: '80%',
    borderStyle: 'solid',
    borderColor: 'red',
    borderWidth: 1,
    paddingLeft: 10,
    backgroundColor: colors.neutral98,
  },
  contentContainer: {
    flex: 8,
    width: '100%',
  },
  bottomContainer: {
    flex: 1,
    width: '100%',
  },

  bottom: {
    flex: 1,
    flexDirection: 'row',
    justifyContent: 'space-around',
    alignItems: 'center',
  },
})

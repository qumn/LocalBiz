import { Pressable, Text, StyleSheet, View } from 'react-native'
import FontAwesome from '@expo/vector-icons/FontAwesome'

export function Button({ icon, text, onPress }) {
  return (
    <View style={styles.buttonContainer}>
      <Pressable style={styles.button} onPress={onPress}>
        <FontAwesome name={icon} size={30} color='#4c4f69' />
        <Text style={styles.text}>{text}</Text>
      </Pressable>
    </View>
  )
}

const styles = StyleSheet.create({
  buttonContainer: {
    width: 60,
    paddingLeft: 3,
    alignItems: 'center',
    justifyContent: 'center',
  },
  button: {
    alignItems: 'center',
    justifyContent: 'center',
    paddingBottom: 10,
  },
  text: {
    fontSize: 10,
    color: '#5c5f77'
  },
})

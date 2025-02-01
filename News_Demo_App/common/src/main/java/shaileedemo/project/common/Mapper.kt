package shaileedemo.project.common

interface Mapper<T : Any?, R> {
    fun map(item: T): R
}